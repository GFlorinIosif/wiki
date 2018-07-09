package com.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.demo.bl.PostBL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.entities.Post;
import com.demo.entities.SubCategory;
import com.demo.entities.User;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.PostRepository;
import com.demo.repositories.SubCategoryRepository;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

	@Autowired
	private PostBL postBL;
	
	@GetMapping(value = {"/searchPost"})
	public Page<Post> searchPost(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchText") String searchText) {
		searchText = searchText.toLowerCase().trim();
		if(searchText.startsWith("autor:")) {
			String autor = searchText.substring(searchText.indexOf(":"), searchText.length()).trim();
			return postBL.getPostListByAuthor(autor, page, size);
		} else {
			return postBL.getPostListByTitle(searchText, page, size);
		}
	}
}
