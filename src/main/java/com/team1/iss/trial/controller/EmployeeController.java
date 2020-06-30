package com.team1.iss.trial.controller;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.impl.LaServiceImpl;
import com.team1.iss.trial.services.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
	@Autowired
	private LaServiceImpl laServiceImpl;

	@Autowired
	private IEmployeeService eService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/employee")
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
    public @ResponseBody void saveLA( LA la) {
        System.out.println(la);
//        laServiceImpl.saveLA(la);
    }
	@RequestMapping(value = "/employee/la", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody void updateLA( LA la) {
		System.out.println(la);
//        laServiceImpl.saveLA(la);
	}
	
	// Get All LAs
	@RequestMapping(value = "/employee/las", method = RequestMethod.GET)
	public String listAllLAs(Model model) {
		model.addAttribute("LA", laServiceImpl.findAll());
		return "las";

	}

	// Get single LA details by provide id
	@RequestMapping(value= "/employee/la/{uid}", method = RequestMethod.GET)
	public String getLAById(Model model, @PathVariable int uid) {
		try {
			LA la = laServiceImpl.getLaById(uid);
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
		laServiceImpl.updateLA(la);
	}

	// Delete an existing LA
	@RequestMapping(value = "/employee/la/{uid}", method = RequestMethod.DELETE)
	public void deleteLA(int uid) {
		laServiceImpl.deleteLA(uid);
	}

	@RequestMapping("/employee/las") 
	public String la(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int uid=userRepository.findUserUidByEmail(email);
		List<LA> las=laServiceImpl.findLaByOwnerId(uid);
		model.addAttribute("laList", las);
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
