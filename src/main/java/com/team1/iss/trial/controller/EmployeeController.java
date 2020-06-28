package com.team1.iss.trial.controller;

import com.team1.iss.trial.domain.LA;
import com.team1.iss.trial.services.impl.LaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private LaServiceImpl laServiceImpl;

	@RequestMapping("")
	public String user() {
		return ("employee/eHome");
	}

	//Get All LAs
	@RequestMapping(value= "/las", method = RequestMethod.GET)
	public String listAllLAs(Model model) {
		model.addAttribute("LA", laServiceImpl.findAll());
		return "las";

	}

	//Get single LA details by provide id
	@RequestMapping(value= "/la/{uid}", method = RequestMethod.GET)
	public String getLAById(Model model, @PathVariable int uid) {
		model.addAttribute("LA",laServiceImpl.getLaById(uid));
		return "laDetails";
	}

	//Create a new LA with full LA details info in Body
	@RequestMapping(value="/la", method = RequestMethod.POST)
	public void saveLA(@RequestBody LA la) {
		laServiceImpl.saveLA(la);
	}

	//Update an existing LA with udpated Body, not sure how to input uid
	@RequestMapping(value="/la/{uid}", method = RequestMethod.PUT)
	public void updateLA(@RequestBody LA la, int uid) {
		laServiceImpl.updateLA(la);
	}

	//Delete an existing LA
	@RequestMapping(value="/la/{uid}", method = RequestMethod.DELETE)
	public void deleteLA(int uid){
		laServiceImpl.deleteLA(uid);
	}

}
