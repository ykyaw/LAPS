package com.team1.iss.trial.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.OverTime;
import com.team1.iss.trial.services.impl.LaServiceImpl;
import com.team1.iss.trial.services.impl.ManagerServiceImpl;
import com.team1.iss.trial.services.interfaces.ILaService;
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
	@Autowired
	private ILaService laservice;
	
	@Autowired
	public void setLaservice(LaServiceImpl laserviceimpl) {
		this.laservice = laserviceimpl;
	}
	
//	@Autowired
//	private IOverTimeService otservice;
//	
//	@Autowired
//	public void setOtservice(OverTimeServiceImpl otserviceimpl) {
//		this.otserivce = otserviceimpl;
//	}
	
	
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
	@RequestMapping("/approveapplication/{uid}")
	public String approveApplication(@PathVariable("uid") int uid, Model model) {
		LA la=laservice.getLaById(uid);
		la.setStatus(CommConstants.ApplicationStatus.APPROVED);
		mservice.saveLA(la);
		model.addAttribute("la", la);
		return "/manager/confirmation";
	}
	

	//reject leave 
	@RequestMapping(value="/rejectapplication",method = RequestMethod.GET)
	public String rejectApplication(String rejectreason,HttpSession session,Model model) {
		LA la=(LA)session.getAttribute("la");
		la.setStatus(CommConstants.ApplicationStatus.REJECTED);
		la.setRejectReason(rejectreason);
		laservice.saveLA(la);
		model.addAttribute("la", la);
		session.removeAttribute("la");
		return "/manager/confirmation";
	}
	
	//key in reject reason
	@RequestMapping("/rejectreasonkeyin/{uid}")
	public String keyInRejectReason(@PathVariable("uid") int uid,Model model,HttpSession session) {
		LA la=laservice.getLaById(uid);
		model.addAttribute("rejectreason", la.getRejectReason());
		session.setAttribute("la", la);
		return "/manager/rejectreason";
	}
	
	//Compensation Claims
	@RequestMapping("/compensationclaims")
	public String compensationclaims(Model model) {
		model.addAttribute("compensationclaims",mservice.findClaims());
		return "/manager/compensationclaims";	
	}
	
//	//Approve Claim
//	@RequestMapping("/approveclaim/{uid}")
//	public String approveClaim(@PathVariable("uid") int uid, Model model) {
//		OverTime ot=otservice.getOverTimeById(uid);
//		ot.setStatus(CommConstants.ClaimStatus.APPROVED);
//		mservice.saveOverTime(ot);
//		model.addAttribute("ot", ot);
//		return "/manager/compensationclaims";
//	}
//		
//	//Reject Claim
//	@RequestMapping("/rejectclaim/{uid}")
//	public String rejectClaim(@PathVariable("uid") int uid, Model model) {
//		LA la=laservice.getLaById(uid);
//		la.setStatus(CommConstants.ClaimStatus.REJECTED);
//		mservice.saveOverTime(ot);
//		model.addAttribute("ot", ot);
//		return "/manager/compensationclaims";
//	}
}






