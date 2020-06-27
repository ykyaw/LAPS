package com.team1.iss.trial.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;


@Controller
public class LoginController {
//	
//	@PostMapping("/login")
//	public String login(@RequestParam("username") String username,
//			@RequestParam("password") String password,
//			Map<String, Object>map, HttpSession session) {
//		if(!StringUtils.isEmpty(username)&&password.equals("123")) {
//			//login successfully redirect page to index
//			session.setAttribute("loginUser", username);
//			return "redirect:index";
//		}else {
//			map.put("msg", "username or password error");
//			return "login";
//		}
//	}

}
