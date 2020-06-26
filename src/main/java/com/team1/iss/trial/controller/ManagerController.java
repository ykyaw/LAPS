package com.team1.iss.trial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@RequestMapping("")
	public String managerHome() {
		return ("manager/mHome");
	}

}
