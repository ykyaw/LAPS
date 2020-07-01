package com.team1.iss.trial.controller;

import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.component.RequestLA;
import com.team1.iss.trial.domain.*;
import com.team1.iss.trial.repo.UserRepository;
import com.team1.iss.trial.services.impl.LaServiceImpl;
import com.team1.iss.trial.services.interfaces.IEmployeeService;
import com.team1.iss.trial.services.interfaces.ILaService;
import com.team1.iss.trial.services.interfaces.IOverTimeService;
import com.team1.iss.trial.services.interfaces.IPublicHolidayService;
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
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
	@Autowired
	private ILaService laService;

	@Autowired
	private IEmployeeService eService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private IOverTimeService overTimeService;

	@Autowired
	private IPublicHolidayService publicHolidayService;

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
		List<User> collect = users.stream().filter(user -> !user.getUserType().equals(CommConstants.UserType.AMDIN))
				.collect(Collectors.toList());
		model.addAttribute("employees",collect);
		return "employee/leave-form";
	}

	/**
	 * ubmit leave application
	 * @param la
	 * @param model
	 * @return
	 */
	@PostMapping("/employee/la")
    public String saveLA(@RequestLA LA la, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int uid=userRepository.findUserUidByEmail(email);
		la.setOwner(new Employee(uid));
		la.setStatus(CommConstants.ApplicationStatus.APPLIED);
		boolean isLAValidate=true;
		if(la.getToTime()-la.getFromTime()<=0){
			model.addAttribute("msg","the to time can not less than from time");
			isLAValidate=false;
		}
		//check if fromtime is a public holiday
		List<PublicHoliday> holidays = publicHolidayService.getCurrentYearPublicHoliday()
				.stream()
				.filter(holiday -> {
					return holiday.getDay() == la.getFromTime();
				})
				.collect(Collectors.toList());
		if(holidays.size()>0){
			model.addAttribute("msg","the from time can not be a public holiday");
			isLAValidate=false;
		}
		//balance check
		//calculate annual application leave duration
		laService.calculateApplicationDuration(la);
		if(la.getType().equals(CommConstants.LeaveType.ANNUAL_LEAVE)){
			float balance = eService.getAnnualApplicationBalance(la);
			if(balance<la.getDuration()){
				model.addAttribute("msg","your annual leave balance is insufficient, only "+balance+" left");
				isLAValidate=false;
			}
		}else if(la.getType().equals(CommConstants.LeaveType.MEDICAL_LEAVE)){
			float balance=eService.getMedicalApplicationBalance(la);
			if(balance<la.getDuration()){
				model.addAttribute("msg","your medical leave balance is insufficient, only "+balance+" left");
				isLAValidate=false;
			}
		}else{
			float balance=eService.getCompensationApplicationBalance(la);
			if(balance<la.getDuration()){
				model.addAttribute("msg","your compensation leave balance is insufficient, only "+balance+" left");
				isLAValidate=false;
			}
		}
		//TODO overlap check
		if(!isLAValidate){
			List<String> applicationType = new ArrayList();
			applicationType.add(CommConstants.LeaveType.ANNUAL_LEAVE);
			applicationType.add(CommConstants.LeaveType.MEDICAL_LEAVE);
			applicationType.add(CommConstants.LeaveType.COMPENSATION_LEAVE);
			model.addAttribute("types", applicationType);
			List<User> users = eService.findAllUsers();
			List<User> collect = users.stream().filter(user -> !user.getUserType().equals(CommConstants.UserType.AMDIN))
					.collect(Collectors.toList());
			model.addAttribute("employees",collect);
			return "employee/leave-form";
		}
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

	/**
	 * show the claim compensation page
	 * @return
	 */
	@GetMapping("/employee/ot")
	public String otPage(){
		return "employee/ot";
	}

	/**
	 * claim an overtime
	 * @param ot
	 * @return
	 */
	@PostMapping("/employee/ot")
	public String submitOT(OverTime ot){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int uid=userRepository.findUserUidByEmail(email);
		ot.setOwner(new Employee(uid));
		ot.setStatus(CommConstants.ClaimStatus.APPLIED);
		overTimeService.saveOt(ot);
		return "redirect:/employee/ots";
	}

	/**
	 * show the overtime list page
	 * @param model
	 * @return
	 */
	@GetMapping("/employee/ots")
	public String otsPage(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		int uid=userRepository.findUserUidByEmail(email);
		List<OverTime> ots=overTimeService.findOtByOwnerId(uid);
		model.addAttribute("ots",ots);
		return "employee/otList";
	}

}
