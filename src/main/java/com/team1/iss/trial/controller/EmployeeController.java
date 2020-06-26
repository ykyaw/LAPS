package com.team1.iss.trial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@RequestMapping("")
	public String user() {
		return ("employee/eHome");
	}
	
	

}
