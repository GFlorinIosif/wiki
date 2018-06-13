package com.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.entities.Post;
import com.demo.entities.User;
import com.demo.repositories.PostRepository;

@Controller
public class SearchController {
	
	@Autowired
	PostRepository postRepo;

	@PostMapping(value = {"/searchPost"})
	public String home(@ModelAttribute("searchText") String searchText, Model model, Principal p) {
		List<Post> listOfPosts = new ArrayList<Post>();
		searchText = searchText.toLowerCase();
		if(searchText.startsWith("autor:")) {
			String autor = searchText.substring(searchText.indexOf(":") + 1, searchText.length()).trim();
			List<Post> allPosts = postRepo.findAll();
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
		return "home";
	}
	
}
