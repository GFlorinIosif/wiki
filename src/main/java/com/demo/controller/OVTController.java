package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OVTController {
	
	@GetMapping("/ovt")
	public String ovt(Model model) {
		model.addAttribute("page", "ovt");
		return "ovt";
	}

}
