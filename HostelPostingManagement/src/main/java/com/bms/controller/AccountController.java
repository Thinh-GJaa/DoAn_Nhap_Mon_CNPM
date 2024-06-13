package com.bms.controller;

import java.security.Principal;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bms.entity.Account;
import com.bms.entity.Constants;
import com.bms.entity.Role;
import com.bms.entity.User;
import com.bms.repository.AccountRepository;
import com.bms.services.UserService;

@Controller
@ControllerAdvice
public class AccountController {

	@Autowired
	public AccountRepository accountRepository;

	@Autowired
	public UserService userService;

	@GetMapping("login")
	public String loginPage(Principal principal) {
		return principal == null ? "login" : "redirect:/home";
	}

	@ExceptionHandler(LockedException.class)
	public String accountBlock(Model model) {
		model.addAttribute("error", "Tài khoản đã bị tạm khóa!");
		return "login";
	}

	@GetMapping("loginFailed")
	public String loginError(Model model) {
		/*
		 * Account account = UserController.getCurrentAccount(); if(account.isBlock()) {
		 * model.addAttribute("error",
		 * "Tài khoản đã tạm khóa, vui lòng liên hệ!"); } else
		 */
		model.addAttribute("error", "Username hoặc mật khẩu không khớp, vui lòng thử lại!");
		return "login";
	}

	@GetMapping("logout")
	public ModelAndView logoutPage(SessionStatus session) {
		SecurityContextHolder.getContext().setAuthentication(null);
		session.setComplete();
		return new ModelAndView("login");
	}

	@GetMapping("register")
	public String registerPage(Principal principal) {
		return principal == null ? "register" : "redirect:/home";
	}

	@PostMapping("/register")
	public String insertUserpage(User user, Account account, Model model, @Param("userType") int userType) {

//		check if user name exist
		boolean isUsernameExist = accountRepository.findByUsername(account.getUsername()).isPresent();
		boolean isPhoneExist = userService.findByPhone(user.getPhone()).isPresent();
		boolean isEmailExist = userService.findByEmail(user.getEmail()).isPresent();

		if (isUsernameExist) {

			model.addAttribute("error", "Username đã tồn tại, vui lòng thử lại!");
			model.addAttribute("userInforTemp", user);
			return "register";
		} else if (isPhoneExist) {

			model.addAttribute("error", "Số điện thoại đã tồn tại, vui lòng thử lại!");
			model.addAttribute("userInforTemp", user);
			return "register";
		} else if (isEmailExist) {

			model.addAttribute("error", "Email đã tồn tại, vui lòng thử lại!");
			model.addAttribute("userInforTemp", user);
			return "register";
		}

//		work factor of bcrypt
		int strength = 10;
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());
		String encodedPassword = bCryptPasswordEncoder.encode(account.getPassword());

		// get time current
		String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

		Role role = new Role();
		if (userType == 1) {
			role.setName(Constants.ROLE_USER_TENANT);
		} else {
			role.setName(Constants.ROLE_USER_OWN);
		}
		Set<Role> roles = new HashSet<Role>();
		roles.add(role);

		// set attribute for account
		account.setPassword(encodedPassword);
		account.setBlock(false);
		account.setRoles(roles);

		boolean isInsertAccount = accountRepository.save(account) != null;

		if (!isInsertAccount) {
			model.addAttribute("error", "Đã xảy ra lỗi, vui lòng thử lại!");
			model.addAttribute("userInforTemp", user);
			return "register";
		}
		// set attribute for user
		user.setCreatedDate(timeStamp);
		user.setUpdateDate(timeStamp);
		user.setAccount(account);
		user.setWarningCount(0);

		boolean isInsertUser = userService.insertUser(user);
		if (!isInsertUser) {
			model.addAttribute("error", "Đã xảy ra lỗi, vui lòng thử lại!");
			model.addAttribute("userInforTemp", user);
			return "register";
		}

		return "redirect:/login";
	}

}
