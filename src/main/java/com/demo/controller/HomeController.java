package com.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.demo.repositories.CategoryRepository;
import com.demo.repositories.PostRepository;
import com.demo.repositories.SubCategoryRepository;

@Controller
public class HomeController {

	@Autowired
	PostRepository postRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	SubCategoryRepository subCategoryRepo;
	
	@GetMapping(value = {"/", "/home"})
	public String home(Model model, Principal p, HttpServletRequest request) {
		model.addAttribute("listOfPosts", postRepo.findAllOrderedByDate());
		model.addAttribute("categorii", categoryRepo.findAll());
		model.addAttribute("subCategorii", subCategoryRepo.findAll());
		model.addAttribute("page", "home");
		return "home";
	}
	
}
