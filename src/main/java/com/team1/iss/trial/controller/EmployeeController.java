package com.team1.iss.trial.controller;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.domain.OverTime;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.impl.LaServiceImpl;
import com.team1.iss.trial.services.interfaces.IEmployeeService;
import com.team1.iss.trial.services.interfaces.ILaService;
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
	private ILaService laService;

	@Autowired
	private IEmployeeService eService;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/employee")
	public String user() {
		return ("employee/eHome");
	}


	/**
	 * go to the apply application page
	 * @param model
	 * @return
	 */
	@GetMapping("/employee/apply")
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

	/**
	 * submit leave application
	 * @param la
	 */
	@PostMapping("/employee/la")
    public String saveLA( LA la) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int uid=userRepository.findUserUidByEmail(email);
		la.setOwner(new Employee(uid));
		la.setStatus(CommConstants.ApplicationStatus.APPLIED);
        System.out.println(la);
		laService.saveLA(la);
		return "redirect:/employee/las";
    }
	@RequestMapping(value = "/employee/la", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public @ResponseBody void updateLA( LA la) {
		System.out.println(la);
//        laServiceImpl.saveLA(la);
	}

	/**
	 * view the leave application detail
	 * @param model
	 * @param uid
	 * @return
	 */
	@GetMapping("/employee/la/{uid}")
	public String getLAById(Model model, @PathVariable int uid) {
		try {
			LA la = laService.getLaById(uid);
			System.out.println(la);
			model.addAttribute("la",la);
			List<String> applicationType = new ArrayList();
			applicationType.add(CommConstants.LeaveType.ANNUAL_LEAVE);
			applicationType.add(CommConstants.LeaveType.MEDICAL_LEAVE);
			applicationType.add(CommConstants.LeaveType.COMPENSATION_LEAVE);
			model.addAttribute("types", applicationType);
			List<User> users = eService.findAllUsers();
			model.addAttribute("employees",users);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "employee/laDetails";
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

	/**
	 * view the employee leave history
	 * @param model
	 * @return
	 */
	@GetMapping("/employee/las")
	public String la(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int uid=userRepository.findUserUidByEmail(email);
		List<LA> las=laService.findLaByOwnerId(uid);
		model.addAttribute("laList", las);
		return ("employee/lalist");
	}

	@GetMapping("/employee/ot")
	public String otPage(){
		return "employee/ot";
	}

	@PostMapping("/employee/ot")
	public String submitOT(OverTime ot){

	}

}
