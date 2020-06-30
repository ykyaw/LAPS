package com.team1.iss.trial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.services.impl.ManagerServiceImpl;
import com.team1.iss.trial.services.interfaces.IManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	
	@Autowired
	private IManagerService mservice;
	
	@Autowired
	public void setMservice(ManagerServiceImpl mServiceImpl) {
		this.mservice = mServiceImpl;
	}
	
	public void tksourcetreetest() {
		
	}
	
	@RequestMapping("")
	public String managerHome() {
		return ("manager/mHome");
	}
	
	@RequestMapping("/listforapproval")
	public String listforapproval(Model model) {
		model.addAttribute("listforapproval",mservice.findPendingApplications());
		return "/manager/listforapproval";	
	}
	
	//show individual application
	@RequestMapping("/individual/{uid}")
	public String individualapplication(@PathVariable("uid") Integer uid,Model model ) {
		model.addAttribute("la", mservice.findLAByID(uid));
		return "/manager/individualapplication";
	}
	
	//approve leave, in detailed leave application html after clicking into certain application
	@RequestMapping("/approveleave")
	public String approveApplication(@ModelAttribute("la")  LA la, Model model) {
		la.setStatus("APPROVED");
		mservice.saveLA(la);
		model.addAttribute("la", la);
		return "/manager/confirmation";
	}
	
	//reject leave 
	@RequestMapping("/rejectleave")
	public String rejectApplication(@ModelAttribute LA la, Model model) {
		//if reject reason is not null
		la.setStatus("REJECTED");
		mservice.saveLA(la);
		model.addAttribute("la", la);
		return "/manager/confirmation";
	}

}
