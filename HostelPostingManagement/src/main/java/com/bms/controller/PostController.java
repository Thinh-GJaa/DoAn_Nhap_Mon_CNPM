package com.bms.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bms.entity.Accomodation;
import com.bms.entity.Category;
import com.bms.entity.Constants;
import com.bms.entity.District;
import com.bms.entity.Image;
import com.bms.entity.Notification;
import com.bms.entity.Post;
import com.bms.entity.ReportPost;
import com.bms.entity.ReportPostId;
import com.bms.repository.AccountRepository;
import com.bms.services.AccomodationService;
import com.bms.services.CategoryService;
import com.bms.services.DistrictService;
import com.bms.services.NotificationService;
import com.bms.services.PostService;
import com.bms.services.ReportPostService;
import com.bms.services.UserService;

@Controller
public class PostController {
	@Autowired
	DistrictService districtService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PostService postService;

	@Autowired
	ReportPostService reportPostService;

	@Autowired
	UserService userService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	AccomodationService accomodationService;

	@Autowired
	AccountRepository accountRepository;

	@RequestMapping("/home")
	public String homePage(Model model, HttpSession session) {

		UserController.loggedAccount = UserController.getCurrentAccount();
		UserController.loggedUser = userService.findByAccount(UserController.loggedAccount).get();

		session.setAttribute("user_fullName", UserController.loggedUser.getFullName());

		if (UserController.loggedAccount.getRoles().iterator().next().getName().equals("ROLE_OWN")) {

			long notificationOfUserAmount = notificationService.countNotifycationNotSeen(UserController.loggedUser);
			long postAmount = postService.countAmountPostOfUser(UserController.loggedUser);
			long postApproveAmount = postService.countAmountPostApprovedOfUser(UserController.loggedUser);
			long postWaitApproveAmount = postService.countAmountPostWaitApproveOfUser(UserController.loggedUser);
			long postHiddenAmount = postService.countAmountPostBlockOfUser(UserController.loggedUser);

			session.setAttribute("notificationOfUserAmount", notificationOfUserAmount);
			session.setAttribute("postAmount", postAmount);
			session.setAttribute("postApproveAmount", postApproveAmount);
			session.setAttribute("postWaitApproveAmount", postWaitApproveAmount);
			session.setAttribute("postHiddenAmount", postHiddenAmount);
		} else if (UserController.loggedAccount.getRoles().iterator().next().getName().equals("ROLE_ADMIN")) {

			long allPostWaitApproveAmount = postService.counAllWaitApprovePost();
			Long allPostReportAmount = reportPostService.postReportAmount();

			session.setAttribute("allPostWaitApproveAmount", allPostWaitApproveAmount);
			session.setAttribute("allPostReportAmount", allPostReportAmount);
		}

		return viewPage(model, 1, session);
	}

	@RequestMapping("/view-posts")
	public String viewPage(Model model, @Param("minPrice") String minPrice, @Param("maxPrice") String maxPrice,
			@Param("minArea") String minArea, @Param("maxArea") String maxArea, @Param("districtId") String districtId,
			@Param("categoryId") String categoryId, HttpSession session) {

		session.setAttribute("minPrice", minPrice);
		session.setAttribute("maxPrice", maxPrice);

		session.setAttribute("districtId", districtId);

		session.setAttribute("minArea", minArea);
		session.setAttribute("maxArea", maxArea);

		session.setAttribute("categoryId", categoryId);
		return viewPage(model, 1, session);
	}

	@RequestMapping("/list-post/page/{pageNum}")
	public String viewPage(Model model, @PathVariable(name = "pageNum") int pageNum, HttpSession session) {

		Category category = null;

		District district = null;

		List<Category> listCategories = categoryService.findAll();
		List<District> listDistricts = districtService.findAll();

		double minArea, maxArea, minPrice, maxPrice;
		minArea = maxArea = minPrice = maxPrice = 0;

		if (session.getAttribute("categoryId") != null) {
			category = categoryService.findById(Integer.parseInt(session.getAttribute("categoryId").toString()));
			district = districtService.findById(Integer.parseInt(session.getAttribute("districtId").toString()));

			minArea = Double.parseDouble(session.getAttribute("minArea").toString());
			maxArea = Double.parseDouble(session.getAttribute("maxArea").toString());
			minPrice = Double.parseDouble(session.getAttribute("minPrice").toString());
			maxPrice = Double.parseDouble(session.getAttribute("maxPrice").toString());
		}

		Page<Post> page = postService.findAllOrderByDesc(1, category, district, minArea, maxArea, minPrice, maxPrice);

		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		model.addAttribute("minArea", minArea);
		model.addAttribute("maxArea", maxArea);
		model.addAttribute("minPrice", minPrice);
		model.addAttribute("maxPrice", maxPrice);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listDistricts", listDistricts);

		return "view-posts";
	}

	@GetMapping("/add-post")
	public String addPostPageGet(Model model) {
		List<Category> listCategories = categoryService.findAll();
		List<District> listDistricts = districtService.findAll();
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listDistricts", listDistricts);
		return "addPost";
	}

	@RequestMapping({ "updatePost", "duplicatePost" })
	public String updatePostPage(Model model, @Param("postId") long postId,
			@Param("isDuplicatePost") String isDuplicatePost) {
		Post post = postService.findById(postId);
		Accomodation accomodation = accomodationService.findByPostid(post);
		List<Category> listCategories = categoryService.findAll();
		List<District> listDistricts = districtService.findAll();

		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listDistricts", listDistricts);
		model.addAttribute("post", post);
		model.addAttribute("accomodation", accomodation);

		if (isDuplicatePost != null) {
			model.addAttribute("dupblicatePost", "true");
		} else {
			model.addAttribute("editPost", "true");
		}
		return "addPost";
	}

	@PostMapping(path = "add-post")
	public String addPostPagePost(Post post, Accomodation accomodation, Model model, HttpSession session,
			@RequestParam(value = "file") CommonsMultipartFile[] files, @RequestParam("categoryId") Integer categoryId,
			@RequestParam("districtId") Integer districtId, @RequestParam("postId") Long postId,
			@RequestParam("accomodationId") Long accomodationId) {
//

		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		District district = districtService.findById(districtId);
		Category category = categoryService.findById(categoryId);

		post.setApprove(false);
		post.setHidden(false);
		post.setActive(true);
		post.setLastUpdate(timeStamp);
		post.setDistrict(district);
		post.setCategory(category);
		post.setUser(UserController.loggedUser);
		post.setCreatedDate(timeStamp);
		String nameFile = "";

		String destination = "";

		List<Image> photos = new ArrayList<Image>();
		if (postId == null) {
			post.setCreatedDate(timeStamp);
		} else {
			post.setId(postId);
			Post post2 = postService.findById(postId);
			post2.setImages(null);
			post.setCreatedDate(post2.getCreatedDate());
		}
		if (files.length != 0) {
			try {
				for (MultipartFile file : files) {
					nameFile = UUID.randomUUID().toString().replace("-", "") + "."
							+ FilenameUtils.getExtension(file.getOriginalFilename());
					destination = Constants.IMAGES_PATH + nameFile;

					Image image = new Image();
					image.setFileName(nameFile);
					image.setPost(post);

					photos.add(image);

					// upload toserver
					File fileSave = new File(destination);
					file.transferTo(fileSave);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Set<Image> images = new HashSet<Image>();
		for (Image image : photos) {
			images.add(image);
		}
		post.setImages(images);

		boolean isAddPost = postService.insertPost(post);

		if (!isAddPost) {
			model.addAttribute("notify", "Đăng bài thất bại, vui lòng thử lại");
			return "addPost";
		}
		accomodation.setPost(post);
		if (accomodationId != null) {
			accomodation.setId(accomodationId);
		}
		boolean isAccomodation = accomodationService.insertAccomodation(accomodation);
		if (!isAccomodation) {
			return "addPost";
		}

		long postAmount = postService.countAmountPostOfUser(UserController.loggedUser);
		long postWaitApproveAmount = postService.countAmountPostWaitApproveOfUser(UserController.loggedUser);

		session.setAttribute("postAmount", postAmount);
		session.setAttribute("postWaitApproveAmount", postWaitApproveAmount);
		return listPostOfUser(model);
	}

	// admin -approve post
	@GetMapping("/approve-post")
	public String approvePostPage(@Param("postId") long postId, Model model) {
		Post post = postService.findById(postId);
		post.setApprove(true);
		post.setHidden(false);
		boolean isApprovePost = postService.insertPost(post);

		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		Notification notification = new Notification(false, timeStamp, Constants.MESSAGE_APPROVE_POST, post.getUser(),
				post);

		notificationService.saveNoti(notification);
		if (!isApprovePost) {
			return "view-wait-aprove-post-admin";
		}
		return listWaitApprovePost(model);
	}

	// user -hide post
	@GetMapping("/user-hide-post")
	public String hindPostPage(@Param("postId") long postId, Model model) {
		Post post = postService.findById(postId);
		if (post.isActive()) {
			post.setActive(false);
		} else {
			post.setActive(true);
		}
		postService.insertPost(post);
		return listApprovePostOfUser(model, 1);
	}

	@GetMapping("/spam-report")
	public String checkSpamReport(@Param("reportPostId") String reportPostId, Model model) {
		ReportPost reportPost = reportPostService.findById(reportPostId);
		reportPost.setSolve(true);
		reportPostService.saveRepostPost(reportPost);
		return listReportPost(model, 1);
	}

	// admin -hide/ not approve post
	// hidden post wrong info
	@GetMapping("/hidden-post")
	public String hiddenPostPage(@Param("postId") long postId, @Param("report") String report,
			@Param("reportPostId") String reportPostId, Model model) {
		Post post = postService.findById(postId);
		post.setHidden(true);
		post.setApprove(false);
		boolean isApprovePost = postService.insertPost(post);
		if (reportPostId != null) {
			ReportPost reportPost = reportPostService.findById(reportPostId);
			reportPost.setSolve(true);
			reportPostService.saveRepostPost(reportPost);

		}
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

		String messageNotiString = "";

		if (report != null) {
			int warningCountUser = post.getUser().getWarningCount();
			post.getUser().setWarningCount(warningCountUser + 1);
			if (warningCountUser == 2) {
				post.getUser().getAccount().setBlock(true);
				accountRepository.save(post.getUser().getAccount());
			} else {
				messageNotiString = Constants.MESSAGE_WARNING_USER;
			}
			userService.insertUser(post.getUser());

		} else {
			messageNotiString = Constants.MESSAGE_NOT_APPROVE_POST;
		}

		Notification notification = new Notification(false, timeStamp, messageNotiString, post.getUser(), post);

		notificationService.saveNoti(notification);

		if (!isApprovePost) {
			return "view-wait-aprove-post-admin";
		}
		return listReportPost(model, 1);
	}

	@GetMapping("/show-post")
	public String unhiddenPostPage(@Param("postId") long postId, Model model) {
		Post post = postService.findById(postId);
		post.setHidden(false);
		post.setApprove(true);
		boolean isApprovePost = postService.insertPost(post);
		if (!isApprovePost) {
			return "view-wait-aprove-post-admin";
		}
		return listHiddenPost(model);
	}

	// admin-view all wait approve post
	@GetMapping("/all-list-post-wait-approve")
	public String listWaitApprovePost(Model model) {
		return viewlistWaitApprovePostPage(model, 1);
	}

	@GetMapping("/all-list-post-wait-approve/page/{pageNum}")
	public String viewlistWaitApprovePostPage(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Post> page = postService.findAllNonApprovePost(pageNum);
		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "view-wait-aprove-post-admin";
	}

	// admin-view all hidden post
	@GetMapping("/all-list-post-hidden")
	public String listHiddenPost(Model model) {
		return listHiddenPost(model, 1);
	}

	@GetMapping("/all-list-post-hidden/page/{pageNum}")
	public String listHiddenPost(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Post> page = postService.findAllHiddenPost(pageNum);
		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "view-hiddent-post-admin";
	}

	// admin-view all report post
	@GetMapping("/all-list-post-report")
	public String listReportPost(Model model) {
		return listReportPost(model, 1);
	}

	@GetMapping("/all-list-post-report/page/{pageNum}")
	public String listReportPost(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<ReportPost> page = reportPostService.findAllReport(pageNum);
		List<ReportPost> listReportPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listReportPosts", listReportPosts);
		return "view-all-report-post";
	}

	// user own- view all wait post of user
	@GetMapping("/list-user-post-wait-approve")
	public String listWaitApprovePostOfUser(Model model) {
		return listWaitApprovePostOfUser(model, 1);
	}

	@GetMapping("/list-user-post-wait-approve/page/{pageNum}")
	public String listWaitApprovePostOfUser(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Post> page = postService.findWaitApproveUserPost(pageNum, UserController.loggedUser);
		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "view-wait-aprove-user-post";
	}

	// user own- view all approve post of user
	@GetMapping("/list-user-post-approved")
	public String listApprovePostOfUser(Model model) {
		return listApprovePostOfUser(model, 1);
	}

	@GetMapping("/list-user-post-approved/page/{pageNum}")
	public String listApprovePostOfUser(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Post> page = postService.findApproveUserPost(pageNum, UserController.loggedUser);
		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "view-aprove-post-user";
	}

	// user own- view all hidden post of user
	@GetMapping("/list-user-post-hidden")
	public String listHiddenPostOfUser(Model model) {
		return listHiddenPostOfUser(model, 1);
	}

	@GetMapping("/list-user-post-hidden/page/{pageNum}")
	public String listHiddenPostOfUser(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Post> page = postService.findHiddenUserPost(pageNum, UserController.loggedUser);
		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "view-user-hidden-post";
	}

	// user own- view all post of user
	@GetMapping("/list-user-post")
	public String listPostOfUser(Model model) {
		return listPostOfUser(model, 1);
	}

	@GetMapping("/list-user-post/page/{pageNum}")
	public String listPostOfUser(Model model, @PathVariable(name = "pageNum") int pageNum) {
		Page<Post> page = postService.findbyUser(pageNum, UserController.loggedUser);
		List<Post> listPosts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listPosts", listPosts);
		return "view-user-post";
	}

	@GetMapping("detail-post")
	public String detailPostPage(@Param("postId") long postId, @Param("notiId") String notiId,
			@Param("seen") String seen, Model model, HttpSession session) {
		if (notiId != null && seen.equals("false")) {
			Notification notification = notificationService.findNotiById(Long.parseLong(notiId)).get();
			notification.setSeen(true);
			notificationService.saveNoti(notification);

			long notificationOfUserAmount = notificationService.countNotifycationNotSeen(UserController.loggedUser);
			session.setAttribute("notificationOfUserAmount", notificationOfUserAmount);
		}
		Post post = postService.findById(postId);
		Accomodation accomodation = accomodationService.findByPostid(post);
		model.addAttribute("post", post);
		model.addAttribute("accomodation", accomodation);
		return "detail-post";
	}

	// user own- view list save post of user
	@GetMapping("/list-user-save-post")
	public String listPosSavetOfUser(Model model) {
		List<Post> listPosts = UserController.loggedUser.getPosts();
		model.addAttribute("listPosts", listPosts);
		return "list-save-post";
	}

	// report post
	@PostMapping("/report-post")
	public String reportPostPage(@RequestParam("postId") long postId, ReportPost reportPost) {
		Post post = postService.findById(postId);
		ReportPostId reportPostId = new ReportPostId(UserController.loggedUser, post);
		reportPost.setPk(reportPostId);
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		reportPost.setCreatedDate(timeStamp);
		reportPost.setId(UUID.randomUUID().toString().replace("-", ""));
		
		boolean isSendReport = reportPostService.saveRepostPost(reportPost);
		if (!isSendReport) {
			return "redirect:/home";
		}
		return "redirect:/home";
	}

	@GetMapping("/show-statiѕticѕ-by-month")
	public String showStatistics(Model model, @Param("monthBegin") String monthBegin) {
		String startMonth = "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM");
		if (monthBegin != null) {
			startMonth = monthBegin;
		} else {
			startMonth = formater.format(Calendar.getInstance().getTime());
		}

		Calendar nextMonth = Calendar.getInstance();

		try {
			nextMonth.setTime(formater.parse(startMonth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		nextMonth.add(Calendar.MONTH, 1);
		String endMonthDate = formater.format(nextMonth.getTime());

		List<Post> posts = postService.statisticByMonth(startMonth, endMonthDate);

		int totalPostOfMonth = posts.size(), totalApprovePostOfMonth = 0, totalnotApprovePost = 0;

		for (Post post : posts) {
			if (post.isApprove())
				totalApprovePostOfMonth++;
			if (post.isHidden())
				totalnotApprovePost++;
		}
		long totalReportPost = reportPostService.postReportAmountofMonth(startMonth, endMonthDate);

		model.addAttribute("totalPostOfMonth", totalPostOfMonth);
		model.addAttribute("totalApprovePostOfMonth", totalApprovePostOfMonth);
		model.addAttribute("totalnotApprovePost", totalnotApprovePost);
		model.addAttribute("totalReportPost", totalReportPost);
		model.addAttribute("month", startMonth);

		return "show-statiѕticѕ-by-month";
	}

	@GetMapping("/show-chart-by-time")
	public String showChartByTime(Model model, @Param("monthBegin") String monthBegin,
			@Param("monthEnd") String monthEnd) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM");

		List<String> monthLists = new ArrayList<String>();
		List<Long> postAmountOfMonthLists = new ArrayList<Long>();
		List<Long> postApproveAmountOfMonthLists = new ArrayList<Long>();
		List<Long> postReportAmountOfMonthLists = new ArrayList<Long>();

		Calendar beginCalendar = Calendar.getInstance();
		Calendar finishCalendar = Calendar.getInstance();

		if (monthBegin != null) {
			try {
				beginCalendar.setTime(formater.parse(monthBegin));
				finishCalendar.setTime(formater.parse(monthEnd));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			while (beginCalendar.before(finishCalendar)) {
				String date = formater.format(beginCalendar.getTime());
				monthLists.add(date);
				beginCalendar.add(Calendar.MONTH, 1);
			}

			monthLists.add(formater.format(finishCalendar.getTime()));
			finishCalendar.add(Calendar.MONTH, 1);
			monthLists.add(formater.format(finishCalendar.getTime()));
			for (int i = 0; i < monthLists.size(); i++) {
				if (i < monthLists.size() - 1) {
					postApproveAmountOfMonthLists
							.add(postService.countPostApproveByMonth(monthLists.get(i), monthLists.get(i + 1)));
					postReportAmountOfMonthLists
							.add(reportPostService.postReportAmountofMonth(monthLists.get(i), monthLists.get(i + 1)));
					postAmountOfMonthLists.add(postService.countPostByMonth(monthLists.get(i), monthLists.get(i + 1)));
				}
			}

			model.addAttribute("monthLists", monthLists);
			model.addAttribute("postApproveAmountOfMonthLists", postApproveAmountOfMonthLists);
			model.addAttribute("postReportAmountOfMonthLists", postReportAmountOfMonthLists);
			model.addAttribute("postAmountOfMonthLists", postAmountOfMonthLists);
			model.addAttribute("monthBegin", monthBegin);
			model.addAttribute("monthEnd", monthEnd);

		}

		return "show-chart-by-time";
	}

}
