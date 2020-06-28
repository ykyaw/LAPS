package com.team1.iss.trial.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.services.impl.EmployeeServiceImpl;
import com.team1.iss.trial.services.interfaces.IEmployeeService;


@Controller
//@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	protected IEmployeeService eService;
	
	@Autowired
	public void setEmployeeService(EmployeeServiceImpl eServiceImpl) {
		this.eService = eServiceImpl;
	}
	
	@RequestMapping("/employee")
	public String home() {
		return "employee/eHome";
	}
	
	@RequestMapping("/employee/lalist")
	public String la(Model model) {
		model.addAttribute("laList", eService.findAllLeave());
		return ("employee/lalist");
	}

	@RequestMapping("/employee/apply")
	public String addLeave(Model model, HttpSession session) {
//		System.out.println(session);
		model.addAttribute("la", new LA());
		model.addAttribute("uid",1);
		model.addAttribute("employee",new User(1));
//		System.out.println("in add leave");
		List<String> applicationType= new ArrayList();
		applicationType.add(CommConstants.LeaveType.ANNUAL_LEAVE);
		applicationType.add(CommConstants.LeaveType.MEDICAL_LEAVE);
		applicationType.add(CommConstants.LeaveType.COMPENSATION_LEAVE);
		model.addAttribute("types", applicationType);
		return ("employee/leave-form");
	}
//		/* Testing using simpler Code to create Leave Application */
//		@RequestMapping("/employee/apply")
//		public String createLA(Model model) {
//		
//		LA la1 = new LA();
//		model.addAttribute("LAModelToHtml", la1);
//		return "employee/leave-form";
//	}
	
	@RequestMapping("/employee/save")
	public String saveLeave(@ModelAttribute("la") @Valid LA la, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("la", la);
			return ("employee/leave-form");
		}
		return "employee/lalist";
	}
}	
