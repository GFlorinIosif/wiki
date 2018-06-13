package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Post;
import com.demo.repositories.PostRepository;

@Controller
public class WelcomeController {
	
	@Autowired
	PostRepository postRepo;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	@GetMapping("/welcome")
	public String welcome(Model model) {
		List<Post> posts = postRepo.findAll();
		model.addAttribute("listOfPosts", posts);
		return "welcome";
	}
}
