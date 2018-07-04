package com.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.demo.entities.File;
import com.demo.entities.Post;
import com.demo.entities.SubCategory;
import com.demo.entities.User;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.FileRepository;
import com.demo.repositories.PostRepository;
import com.demo.repositories.SubCategoryRepository;
import com.demo.repositories.UserRepository;

@Controller
public class PostController {

	private Logger log = Logger.getLogger("[SPRING BOOT - EC's WIKI]");;

	private static String UPLOADED_FOLDER = "D://upload//temp//";
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	FileRepository fileRepo;
	
	@Autowired
	CategoryRepository categoryRepo;
	
	@Autowired
	SubCategoryRepository subCategoryRepo;

	@GetMapping(value = {"/getPostList"})
	@ResponseBody
	public List<Post> getPostList(Principal p) {
		List<Post> postList = postRepo.findAllOrderedByDate();
		log.info("getPostList route called");
		return postList;
	}

	@PostMapping(value = {"/savePost"})
	@ResponseBody
	public String home(@ModelAttribute("titlu") String titlu, @ModelAttribute("descriere") String descriere, @RequestParam("files") MultipartFile[] files, 
			@ModelAttribute("idCategorie") long idCategorie, @RequestParam("subCategorii") long [] subCategorii, Model model, Principal p) {
		
		Set<File> savedFiles = new HashSet<File> ();
		if (files != null && files.length > 0) {
			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					try {
						byte[] bytes = file.getBytes();
						UUID uuid = UUID.randomUUID();
						String fileName = file.getOriginalFilename();
						String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
						String guid = uuid + "." + fileExtension;
						Path path = Paths.get(UPLOADED_FOLDER + guid);
						Files.write(path, bytes);
						File bdFile = new File();
						bdFile.setNume(fileName);
						bdFile.setCale(UPLOADED_FOLDER + guid);
						bdFile.setTipContinut(file.getContentType());
						bdFile.setDimensiune(file.getSize());
						bdFile.setGuid(guid);
						File savedFile = fileRepo.save(bdFile);
						savedFiles.add(savedFile);
					} catch (IOException e) {
						e.printStackTrace();
						model.addAttribute("ok", false);
					}
				}
			}
		}
		
		Post post = new Post();
		User loggedUser = userRepo.findByUserName(p.getName());
		post.setAuthor(loggedUser);
		post.setTitlu(titlu);
		post.setDescriere(descriere);
		post.setFiles(savedFiles);
		Set<SubCategory> postSubCategories = new HashSet<SubCategory> ();
		if(subCategorii != null && subCategorii.length > 0) {
			for(long subCatId : subCategorii) {
				SubCategory subCat = subCategoryRepo.findOne(subCatId);
				postSubCategories.add(subCat);
			}
		}
		post.setSubCategori(postSubCategories);
		Post savedPost = postRepo.save(post);
		if(null == savedPost) {
			model.addAttribute("nok", true);
		} else {
			model.addAttribute("ok", true);
			
			model.addAttribute("listOfPosts", postRepo.findAllOrderedByDate());
		}
		
		model.addAttribute("page", "home");
		return "home";
	}

}
