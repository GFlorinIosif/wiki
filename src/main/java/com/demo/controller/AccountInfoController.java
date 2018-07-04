package com.demo.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountInfoController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/saveAccountInfo")
	@ResponseBody
	public String saveAccountInfo(@ModelAttribute("nume") String nume, @ModelAttribute("prenume") String prenume, @ModelAttribute("oldPassword") String oldPassword, 
				@ModelAttribute("newPassword") String newPassword, @ModelAttribute("confirmPassword") String confirmPassword, Model model, HttpServletRequest request) {
		User succesUpdate = null;
		try {
			User user = (User) request.getSession().getAttribute("user");
			boolean changePassword = (oldPassword != null && oldPassword.length() > 0);
			if(changePassword) {
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//				String oldPassEncoded = encoder.encode(oldPassword);
				if(!encoder.matches(oldPassword, user.getPassword())) {
					model.addAttribute("wrongPassword", true);
					return "/accountInfo";
				} else if (!newPassword.equals(confirmPassword)) {
					model.addAttribute("passwordMissMatch", true);
					return "/accountInfo";
				} else {
					user.setPassword(encoder.encode(newPassword));
				}
			}
			
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
