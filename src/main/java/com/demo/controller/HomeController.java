package com.demo.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.Post;
import com.demo.entities.User;
import com.demo.repositories.PostRepository;
import com.demo.repositories.UserRepository;

@Controller
public class HomeController {

	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping(value = {"/", "/home"})
	public String home(Model model, Principal p, HttpServletRequest request) {
		if(p != null) {
			User loggedUser = userRepo.findByUserName(p.getName());
			request.getSession().setAttribute("user", loggedUser);
		}
		
		model.addAttribute("listOfPosts", postRepo.findAll());
		return "home";
	}
	
	@PostMapping(value = {"/", "/home"})
	public String home(@ModelAttribute("titlu") String titlu, @ModelAttribute("descriere") String descriere, Model model, Principal p) {
		Post post = new Post();
		User loggedUser = userRepo.findByUserName(p.getName());
		post.setAuthor(loggedUser);
		post.setTitlu(titlu);
		post.setDescriere(descriere);
		post.setDataAdaugare(new Date());
		Post savedPost = postRepo.save(post);
		if(null == savedPost) {
			model.addAttribute("nok", true);
		} else {
			model.addAttribute("ok", true);
			model.addAttribute("listOfPosts", postRepo.findAll());
		}
		return "home";
	}
	
}
