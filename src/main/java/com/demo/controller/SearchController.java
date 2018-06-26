package com.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.entities.Post;
import com.demo.entities.SubCategory;
import com.demo.entities.User;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.PostRepository;
import com.demo.repositories.SubCategoryRepository;

@Controller
public class SearchController {
	
	@Autowired
	PostRepository postRepo;

	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	SubCategoryRepository subCategoryRepo;
	
	@GetMapping(value = {"/searchPost"})
	public String home(@ModelAttribute("searchText") String searchText, Model model, Principal p) {
		List<Post> listOfPosts = new ArrayList<Post>();
		searchText = searchText.toLowerCase().trim();
		if(searchText.startsWith("autor:")) {
			String autor = searchText.substring(searchText.indexOf(":"), searchText.length()).trim();
			List<Post> allPosts = postRepo.findAllOrderedByDate();
			for(Post post : allPosts) {
				User postUser = post.getAuthor();
				if(postUser.getNume().toLowerCase().contains(autor) || postUser.getPrenume().toLowerCase().contains(autor)) {
					listOfPosts.add(post);
				}
			}
		} else {
			listOfPosts = postRepo.findAllByTitle(searchText);
		}
		model.addAttribute("listOfPosts", listOfPosts);
		model.addAttribute("categorii", categoryRepo.findAll());
		model.addAttribute("subCategorii", subCategoryRepo.findAll());
		model.addAttribute("page", "search");
		return "home";
	}
	
	@GetMapping("/filterPost")
	public String home(@ModelAttribute ArrayList<SubCategory> subCategorii, Model model, Principal p) {
		model.addAttribute("listOfPosts", postRepo.findAllByTitle("link"));
		model.addAttribute("categorii", categoryRepo.findAll());
		model.addAttribute("subCategorii", subCategoryRepo.findAll());
		model.addAttribute("page", "home");
		return "home";
	}
	
}
