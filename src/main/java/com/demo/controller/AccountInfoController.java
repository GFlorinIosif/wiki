package com.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Controller
public class AccountInfoController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/accountInfo")
	public String accountInfo(Model model, Principal p, HttpServletRequest request) {
		User user = userRepository.findByUserName(p.getName());
		request.getSession().setAttribute("user", user);
		return "/accountInfo";
	}
	
	@PostMapping("/saveAccountInfo")
	public String saveAccountInfo(@ModelAttribute("nume") String nume, @ModelAttribute("prenume") String prenume, Model model, HttpServletRequest request) {
		User succesUpdate = null;
		try {
			User user = (User) request.getSession().getAttribute("user");
			user.setNume(nume);
			user.setPrenume(prenume);
			succesUpdate = userRepository.save(user);
			request.getSession().setAttribute("user", succesUpdate);
		} catch (Exception e) {
			System.out.println("EROARE LA MODIFICARE INFORMATII UTILIZATOR");
		}
		
		if(succesUpdate != null) {
			model.addAttribute("ok", true);
		} else {
			model.addAttribute("nok", true);
		}
		return "/accountInfo";
	}

}
