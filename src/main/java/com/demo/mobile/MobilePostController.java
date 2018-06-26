package com.demo.mobile;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.entities.Post;
import com.demo.repositories.PostRepository;

@Controller
public class MobilePostController {
	
	private Logger log = Logger.getLogger("[SPRING BOOT - EC's WIKI]");;
	
	@Autowired
	PostRepository postRepo;

	@GetMapping(value = {"/getPostList"})
	@ResponseBody
	public List<Post> home() {
		List<Post> postList = postRepo.findAllOrderedByDate();
		log.info("getPostList route called");
		return postList;
	}
}
