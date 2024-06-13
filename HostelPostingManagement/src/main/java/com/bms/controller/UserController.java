package com.bms.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bms.dao.AccountDetailsDTO;
import com.bms.entity.Account;
import com.bms.entity.Notification;
import com.bms.entity.Post;
import com.bms.entity.User;
import com.bms.repository.AccountRepository;
import com.bms.services.NotificationService;
import com.bms.services.PostService;
import com.bms.services.UserService;

@Controller
public class UserController {

	public static Account loggedAccount;
	public static User loggedUser;

	@Autowired
	UserService userService;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	PostService postService;

	@Autowired
	NotificationService notificationService;

	private static final void validatePrinciple(Object principle) {
		if (!(principle instanceof AccountDetailsDTO)) {
			throw new IllegalArgumentException("Principle can not null");
		}
	}

	public static final Account getCurrentAccount() {
		UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder
				.getContext().getAuthentication();

		validatePrinciple(authenticationToken.getPrincipal());
		return ((AccountDetailsDTO) authenticationToken.getPrincipal()).getAccountDetails();
	}

	@GetMapping("/user-profile")
	public String userProfile(Model model) {
		UserController.getCurrentAccount();
		UserController.loggedUser = userService.findByAccount(UserController.loggedAccount).get();
		model.addAttribute("user", loggedUser);
		return "edit-profile";
	}

	@PostMapping("/edit-profile")
	public String editProfilePage(User user, Model m) {
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
		user.setAccount(loggedUser.getAccount());
		user.setCreatedDate(loggedUser.getCreatedDate());
		user.setUpdateDate(timeStamp);
		user.setId(loggedUser.getId());

		boolean isResult = userService.insertUser(user);
		if (!isResult) {
			return "edit-profile";
		}
		return userProfile(m);
	}

//	@GetMapping("/reset-password")
//	public String resetPaswordPageGet(Model model) {
//		return "resetPasword";
//	}

//	@PostMapping("/reset-password")
//	public String resetPaswordPagePost(Account account, @Param("passwordOld") String passwordOld, Model model) {
//		int strength = 10;
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
//		String encodedPasswordOld = bCryptPasswordEncoder.encode(passwordOld);
//		System.out.println(loggedAccount.getPassword());
//		System.out.println(encodedPasswordOld);
//		boolean isPasswordMatch = loggedAccount.getPassword().equals(encodedPasswordOld);
//		if (!isPasswordMatch) {
//			model.addAttribute("error", "Mật khẩu cũ không trùng khớp, vui lòng thử lại!");
//			return "resetPasword";
//		}
//		String encodedPasswordNew = bCryptPasswordEncoder.encode(account.getPassword());
//		
//		account.setPassword(encodedPasswordNew);
//		account.setBlock(false);
//		account.setUsername(loggedAccount.getUsername());
//		
//		boolean isUpdateAccount = accountRepository.save(account) != null;
//		
//		if(!isUpdateAccount) {
//			model.addAttribute("error", "Đã xảy ra lỗi, vui lòng thử lại!");
//			return "reset-password";
//		}
//
//		return "redirect:/home";
//	}

	@GetMapping("/block-user")
	public String blockUserPage(@Param("userId") long userId, Model model) {
		User user = userService.findById(userId).get();
		Account account = user.getAccount();
		account.setBlock(true);
		boolean isBlockAccount = accountRepository.save(account) != null;
		if (!isBlockAccount) {
			model.addAttribute("result", "Khóa user thất bại!");
			return "view-users";
		}
		model.addAttribute("result", "Khóa user thành công!");
		return "redirect:/all-user";
	}

	@GetMapping("/active-user")
	public String activeUserPage(@Param("userId") long userId, Model model) {
		User user = userService.findById(userId).get();
		Account account = user.getAccount();
		account.setBlock(false);
		boolean isBlockAccount = accountRepository.save(account) != null;
		if (!isBlockAccount) {
			model.addAttribute("result", "Mở Khóa user thất bại!");
			return "view-users";
		}
		model.addAttribute("result", "Mở Khóa user thành công!");
		return "redirect:/all-user";
	}

	// admin-view all user
	@GetMapping("/all-user")
	public String listUserPage(Model model, @Param("keyword") String keyword, HttpSession session) {
		System.out.println("kiem tra user");
		if(keyword!=null) {
			System.out.println(keyword);
			session.setAttribute("keyword", keyword);
		}else {
			session.removeAttribute("keyword");
		}
		return listUserpage(model, 1, session);
	}

	@GetMapping("/view-user/page/{pageNum}")
	public String listUserpage(Model model, @PathVariable(name = "pageNum") int pageNum, HttpSession session) {
		String keyword = "";
		if (session.getAttribute("keyword") != null) {
			keyword = session.getAttribute("keyword").toString();
		}
		System.out.println(keyword);
		
		Page<User> page = userService.listAll(pageNum, keyword);
		List<User> listUsers = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listProducts", listUsers);
		model.addAttribute("keyword", keyword);
		return "view-user";
	}

	@GetMapping("/add-save-post")
	public String addSavePostPage(@Param("postId") long postId) {

		for (Post post : loggedUser.getPosts()) {
			if (post.getId() == postId) {
				return "redirect:/home";
			}
		}

		Post postTemp = postService.findById(postId);
		loggedUser.addLikePost(postTemp);

		boolean saveUserPost = userService.insertUser(loggedUser);

		if (!saveUserPost) {
			return "redirect:/home";
		}

		return "redirect:/home";
	}

	@GetMapping("/remove-save-post")
	public String removeSavePostPage(@Param("postId") long postId) {

		loggedUser.getPosts().removeIf(post -> post.getId() == postId);

		userService.deleteSaveList(postId, loggedUser.getId());
		System.out.println("postid " + postId);
		System.out.println("loggedUser " + loggedUser.getId());
		return "redirect:/list-user-save-post";
	}

	@GetMapping("forget-password")
	public String resetPasswordPage() {
		return "reset-password";
	}

	@PostMapping("reset-password")
	public String resetPasswordPagePost(@RequestParam("username") String username, @RequestParam("phone") String phone,
			@RequestParam("email") String email, Model model, @RequestParam("newPassword") String newPassword) {

		Optional<Account> account = accountRepository.findByUsername(username);
		if (!account.isPresent()) {
			model.addAttribute("error", "User name không tồn tại");
			return "reset-password";
		}
		User user = userService.findByAccount(account.get()).get();
		System.out.println(user.getEmail() + " " + email);
		System.out.println(user.getPhone() + " " + phone);

		if (!user.getEmail().equals(email)) {
			model.addAttribute("error", "Mail không đúng");
			return "reset-password";
		}

		if (!user.getPhone().equals(phone)) {
			model.addAttribute("error", "Số điện thoại không đúng");
			return "reset-password";
		}

		int strength = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(newPassword);

		account.get().setPassword(encodedPassword);

		boolean isResetPassword = accountRepository.save(account.get()) != null;

		if (!isResetPassword) {
			model.addAttribute("error", "Thay đổi mật khẩu thất bại, vui lòng thử lại");
			return "reset-password";
		}

		return "redirect:/login";
	}

	@GetMapping("list-user-notification")
	public String listNotificationUser(Model model) {
		List<Notification> notifications = new ArrayList<Notification>();

		notifications = notificationService.listNotiOfuser(loggedUser);

		model.addAttribute("notifications", notifications);

		return "list-user-notification";
	}

	@GetMapping("setAllSeen")
	public String setAllSeenPage(Model model,HttpSession session) {
		notificationService.setAllSeen(loggedUser.getId());
		long notificationOfUserAmount = notificationService.countNotifycationNotSeen(UserController.loggedUser);
		session.setAttribute("notificationOfUserAmount", notificationOfUserAmount);
		return "redirect:/list-user-notification";
	}
}
