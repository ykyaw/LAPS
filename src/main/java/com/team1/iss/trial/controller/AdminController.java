package com.team1.iss.trial.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.iss.trial.common.CommConstants;

import com.team1.iss.trial.domain.Manager;

import com.team1.iss.trial.domain.PublicHoliday;

import com.team1.iss.trial.domain.MassLeaveForm;

import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.domain.FormEditUser;
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
	
	@RequestMapping("/admin/aHome")
	public String adminHome() {
		return ("admin/aHome");
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> u1 = aservice.findAll();
		for(User u:u1) {
			u.setPassword(""); //so as not to send password over
		}
		model.addAttribute("userlist", u1);
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
		return "admin/aShowAllUsers";
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
		
		return "admin/aShowAllUsers";
	}

}

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
