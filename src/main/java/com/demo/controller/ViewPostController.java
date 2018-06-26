package com.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.entities.Post;
import com.demo.repositories.PostRepository;
import com.demo.repositories.UserRepository;

@Controller
public class ViewPostController {
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("/viewPost")
	public String viewPost(@ModelAttribute("postId") long postId, Model model, Principal p, HttpServletRequest request) {
		Post post = postRepo.findOne(postId);
		model.addAttribute("post", post);
		return "viewPost";
	}

	
}
