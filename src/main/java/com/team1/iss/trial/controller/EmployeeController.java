package com.team1.iss.trial.controller;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.services.impl.LaServiceImpl;
import com.team1.iss.trial.services.interfaces.IEmployeeService;
import com.team1.iss.trial.services.interfaces.ILaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
	@Autowired
	private ILaService laService;

	@Autowired
	private IEmployeeService eService;

	@RequestMapping("/employee/eHome")
	public String user() {
		return ("employee/eHome");
	}


	@RequestMapping("/employee/apply")
	public String addLeave(Model model) {
		List<String> applicationType = new ArrayList();
		applicationType.add(CommConstants.LeaveType.ANNUAL_LEAVE);
		applicationType.add(CommConstants.LeaveType.MEDICAL_LEAVE);
		applicationType.add(CommConstants.LeaveType.COMPENSATION_LEAVE);
		model.addAttribute("types", applicationType);
		List<User> users = eService.findAllUsers();
		model.addAttribute("employees",users);
		return "employee/leave-form";
	}

    // Create a new LA with full LA details info in Body
    @RequestMapping(value = "/employee/la", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public @ResponseBody void saveLA(LA la, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
		System.out.println(la);
        laService.saveLA(la);
    }

	
	// Get All LAs
	@RequestMapping(value = "/employee/las", method = RequestMethod.GET)
	public String listAllLAs(Model model) {
		model.addAttribute("LA", laService.findAll());
		return "las";

	}

	// Get single LA details by provide id
	@RequestMapping(value= "/employee/la/{uid}", method = RequestMethod.GET)
	public String getLAById(Model model, @PathVariable int uid) {
		try {
			LA la = laService.getLaById(uid);
			model.addAttribute("LA",la);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "laDetails";
	}



	// Update an existing LA with udpated Body, not sure how to input uid
	@RequestMapping(value = "/employee/la/{uid}", method = RequestMethod.PUT)
	public void updateLA(@RequestBody LA la, int uid) {
		laService.updateLA(la);
	}

	// Delete an existing LA
	@RequestMapping(value = "/employee/la/{uid}", method = RequestMethod.DELETE)
	public void deleteLA(int uid) {
		laService.deleteLA(uid);
	}

	@RequestMapping("/employee/lalist")
	public String la(Model model) {
		model.addAttribute("laList", eService.findAllLeave());
		return ("employee/lalist");
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
