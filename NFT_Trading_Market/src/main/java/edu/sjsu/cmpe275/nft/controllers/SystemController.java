package edu.sjsu.cmpe275.nft.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@CrossOrigin
public class SystemController {

	@RequestMapping(value = "/systemDashboard", method = RequestMethod.GET)
	public String viewSystemDashboard() {
		return "systemDashboard";
	}
	
	
}
