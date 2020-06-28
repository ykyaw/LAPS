package com.team1.iss.trial.controller;



import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.iss.trial.common.CommConstants;

import com.team1.iss.trial.domain.Manager;
import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.domain.UserForm;
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
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<UserForm> uf = aservice.findAllwithManagerName();
		model.addAttribute("userlist", uf);
		return "admin/aShowAllUsers";
	}
	
	@RequestMapping("/edit/{username}")
	public String editForm(@PathVariable("username")String username, Model model) {
		User u1 = aservice.findUserByUsername(username);

		model.addAttribute("userlist", u1);
		
		return "admin/aFormEditUsers";
	}
	

//	@RequestMapping("/save")
//	public String saveUser(@ModelAttribute("user")User user, Model model) {
//		aservice.saveUser(user);
//		return "forward:/admin/list";
//	}
	

	
	
	@RequestMapping("/updaterole")
	public void updateRole() {
		aservice.updateUserType(CommConstants.UserType.EMPLOYEE,1);
//		User user = userRepository.findById(1).get();
//		user.setUserType(CommConstants.UserType.EMPLOYEE);
	}
	
	@RequestMapping("/create")
	public String CreateUser(Model model) {
		User u1=new User();
		model.addAttribute("usertohtml",u1); // key value pair (can start using usertohtml.userId, usertohtml.userName in the template view page.)
		
		return "admin/acreateuser";
	}
	
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("usertohtml") User user,  Model model) {

		aservice.convertuser(user);
		
//		if(user.getUserType().equals(CommConstants.UserType.AMDIN)) {
//			User uu = (Admin) user;
//			aservice.saveUser(uu);
//		
//		} else if (user.getUserType().equals(CommConstants.UserType.EMPLOYEE)) {
//			User uu = (Employee) user;
//			aservice.saveUser(uu);
//		} else () {
//			User uu = (Manager) user;
//			aservice.saveUser(uu);
//		}
		
		return "admin/aShowAllUsers";
	}

	

    
    
 
	
	
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
}
