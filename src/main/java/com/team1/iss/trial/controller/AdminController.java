package com.team1.iss.trial.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team1.iss.trial.domain.User;
import com.team1.iss.trial.services.impl.AdminServiceImpl;
import com.team1.iss.trial.services.interfaces.IAdminService;




@Controller
//@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private IAdminService aservice; // Remember to create the service INTERFACE, not the service class
	
	@Autowired
	public void setIAdminServices(AdminServiceImpl aservices) { //This is doing a setter DI for services interface
		this.aservice = aservices; //assigning the services to this instance of service
	}
	
	@RequestMapping("")
	public String adminHome() {
		return ("admin/aHome");
	}
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userlist", aservice.findAll());
		return "admin/aShowAllUsers";
	}
	
	@RequestMapping("/edit/{username}")
	public String editForm(@PathVariable("username")String username, Model model) {
		User u1 = aservice.findUserByUsername(username);
		model.addAttribute("userlist", u1);
		
		return "admin/aFormEditUsers";
	}
	
	@RequestMapping("/save")
	public String saveUser(@ModelAttribute("user")User user, Model model) {
		aservice.saveUser(user);
		return "forward:/admin/list";
	}
	


}
