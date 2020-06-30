package com.team1.iss.trial.controller;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.web.bind.annotation.RequestParam;


import com.team1.iss.trial.common.CommConstants;
import com.team1.iss.trial.common.utils.TimeUtil;
import com.team1.iss.trial.domain.Manager;

import com.team1.iss.trial.domain.PublicHoliday;

import com.team1.iss.trial.domain.MassLeaveForm;

import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.domain.FormEditUser;
import com.team1.iss.trial.domain.FormPh;
import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.repo.ManagerRepository;

import com.team1.iss.trial.domain.Admin;
import com.team1.iss.trial.domain.Employee;
import com.team1.iss.trial.domain.Manager;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.repo.UserRepository;

import com.team1.iss.trial.services.impl.AdminServiceImpl;
import com.team1.iss.trial.services.interfaces.IAdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService aservice; // Remember to create the service INTERFACE, not the service class
	
	@Autowired
	public void setIAdminServices(AdminServiceImpl aservicesimpl) { //This is doing a setter DI for services interface
		this.aservice = aservicesimpl; //assigning the services to this instance of service
	}
	
	@RequestMapping("")
	public String adminHome() {
		return ("admin/aHome");
	}
	
	/*
	 * @RequestMapping("/list") public String list(Model model) { List<User> u1 =
	 * aservice.findAll(); for(User u:u1) { u.setPassword(""); //so as not to send
	 * password over } model.addAttribute("userlist", u1); return
	 * "admin/aShowAllUsers"; }
	 */
	
	@RequestMapping("/list")
	public String list() {
		return "forward:/admin/list/1";
	}	
	@RequestMapping("/list/{page}")
	public String listByPagination(@PathVariable("page") int page, Model model) {
		PageRequest pageable = PageRequest.of(page-1, 3);
		Page<User> userpage = aservice.getPaginatedUsers(pageable);
        int totalPages = userpage.getTotalPages();
        if(totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		model.addAttribute("userList", userpage.getContent());
		return "admin/aShowAllUsers";
	}
	
	
	
	
	@RequestMapping("/updatemg/{uid}")
	public String updateManager(@PathVariable("uid")int uid, Model model) {
		User u1 = aservice.findUserById(uid);
		List<Manager> m1 = aservice.findAllManager();
        model.addAttribute("u1tohtml",u1);
        model.addAttribute("m1tohtml",m1);
		return "admin/aFormEditUserManager";
	}	
	@RequestMapping("/updatemg")
	public String saveupdatedManager(@ModelAttribute("usertohtml") User user,  Model model) {
		aservice.updateUserManager(user.getManager().getUid(), user.getUid());
		return "forward:/admin/list";
	}
	
	@RequestMapping("/edit/{uid}")
	public String editForm(@PathVariable("uid")int uid, Model model) {
		FormEditUser uf = aservice.editUser(uid);
		List<String> usertype = new ArrayList();
		usertype.add(CommConstants.UserType.AMDIN);
		usertype.add(CommConstants.UserType.EMPLOYEE);
		usertype.add(CommConstants.UserType.MANAGER);
		model.addAttribute("edituserform", uf);
		model.addAttribute("usertypetohtml", usertype);
		return "admin/aFormEditUsers";
	}
	
	@RequestMapping("/save_edit")
	public String saveEditedUser(@ModelAttribute("edituserform") FormEditUser userform,  Model model) {
		aservice.updateUser(userform);	
		return "forward:/admin/list";
	}
	
	
	@RequestMapping("/create")
	public String CreateUser(Model model) {
		User u1= new User();
		List<Manager> m1 = aservice.findAllManager(); 
		model.addAttribute("usertohtml",u1); // key value pair (can start using usertohtml.userId, usertohtml.userName in the template view page.)
		model.addAttribute("managertohtml",m1);
		return "admin/acreateuser";
	}
	
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("usertohtml") User user, Model model) {
		aservice.saveNewUser(user);
		int newlyaddedUID = aservice.findlatestUID();
		aservice.updateUserManager(user.getManager().getUid(), newlyaddedUID);	
		return "forward:/admin/list";
	}
	
	@RequestMapping("/massEditLeave")
	public String massEditLeave(Model model) {
		MassLeaveForm form = new MassLeaveForm();
		model.addAttribute("mldays", form);
		return "admin/aFormEditleave";
	}

	@RequestMapping("/massSaveLeave")
	public String massSaveLeave(@ModelAttribute("mldays") MassLeaveForm MLForm, Model model) {
		MassLeaveForm a = MLForm;
		
		aservice.updateAllMedicalLeave(MLForm.getMedicalLeaveEntitlement());
		aservice.updateProfAnnualLeave(MLForm.getAnnualLeaveEntitlementProf());
		aservice.updateAdminAnnualLeave(MLForm.getAnnualLeaveEntitlementAdmin());
		
		return "forward:/admin/list";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public String getUser(@RequestParam (value = "word", required = false) String word, Model model) {
		
		List<User> users= aservice.getAllUsers(word); //check to name and email
	    model.addAttribute("users", users);
	    return "admin/aSearchedResults";
	}
	
	@RequestMapping("/ph")
	public String addPh(Model model) {
		List<PublicHoliday> phlist = aservice.getAllPH();
		List<FormPh>phform = new ArrayList();
		for(PublicHoliday p: phlist) {
			FormPh h = new FormPh();
			h.setUid(p.getUid());
			h.setDay(TimeUtil.convertTimestampToyyyMMdd(p.getDay()));
			h.setName(p.getName());
			phform.add(h);
		}	
		model.addAttribute("phform", phform);
		model.addAttribute("phform2", new FormPh());
		return "admin/aFormPubholiday";
	}
	
	@RequestMapping("/saveph")
	public String savePh(@ModelAttribute("phform") FormPh phform) {
		aservice.savePh(phform);
		return "forward:/admin/ph";
	}

}


//// Create a new LA with full LA details info in Body
//@RequestMapping(value = "/employee/la", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//public @ResponseBody void saveLA(LA la) {
//    System.out.println(la);
////    laServiceImpl.saveLA(la);
//}

//@RequestMapping("/save")
//public String saveUser(@ModelAttribute("user")User user, Model model) {
//	aservice.saveUser(user);
//	return "forward:/admin/list";
//}


//@RequestMapping("/updaterole")
//public void updateRole() {
//	aservice.updateUserType(CommConstants.UserType.EMPLOYEE,1);
//	User user = userRepository.findById(1).get();
//	user.setUserType(CommConstants.UserType.EMPLOYEE);
//}
/*	
@RequestMapping("/update/{usertype}") //follow up, need to pass in uid from UI form to make the edits
public String updateUser(@PathVariable("usertype") String usertype) {
	if(usertype.equals("a"))
		aservice.updateUserType(CommConstants.UserType.AMDIN, 1);
	if(usertype.equals("m"))
		aservice.updateUserType(CommConstants.UserType.MANAGER, 1);
	if(usertype.equals("e"))
		aservice.updateUserType(CommConstants.UserType.EMPLOYEE, 1);	
	return "<h1> Done! </h1>";
}

@RequestMapping("/updatemg")
public String updateManager() {
	aservice.updateUserManager(4, 1);
	return "";
}

*/
