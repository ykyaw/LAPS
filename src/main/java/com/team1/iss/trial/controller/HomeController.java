package com.team1.iss.trial.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class HomeController {

	@Autowired
	IUserService userService;
	
	@RequestMapping("/")
	public String home() {
		return"index";
	}

	@PostMapping("/userlogin")
	public String Login(@RequestParam("email") String email,
						@RequestParam("password") String password,
						Map<String,Object> map, HttpSession session){
		User user = userService.login(email, password);
		if(user!=null){
			session.setAttribute("user",user);
			if(user.getUserType().equals(CommConstants.UserType.MANAGER)){
				return"redirect:manager/mHome";
			}

			if(user.getUserType().equals(CommConstants.UserType.AMDIN)){
				return"redirect:admin/aHome";
			}

			if(user.getUserType().equals(CommConstants.UserType.EMPLOYEE)){
				return"redirect:employee/eHome";
			}
		}
		map.put("msg","email or password incorrect");
		return "login";
	}

	@RequestMapping("/accessDenied")
	public String denied() {
		return "security/denied";
	}
	
	@RequestMapping("/login")
	public String loginpage() {
		return "security/login";
	}
	
	
	@RequestMapping("/logout-success")
	public String logoutpage() {
		return "security/logout";
	}

}
