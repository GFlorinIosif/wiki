package com.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.repositories.UserRepository;

@Controller
public class DocumentsController {
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("/documents")
	public String documents(Model model, HttpServletRequest request, Principal p) {
		model.addAttribute("page", "documents");
		return "documents";
	}
	
}
