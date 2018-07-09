package com.demo.controller;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.*;
import java.util.logging.Logger;

import com.demo.bl.PostBL;
import com.demo.entities.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class PostController {

	private Logger log = Logger.getLogger("[SPRING BOOT - EC's WIKI]");;

	private static String UPLOADED_FOLDER = "D://upload//temp//";

	@Autowired
	private PostBL postBL;

	@GetMapping(value = {"/getPostList"})
	@ResponseBody
	public Page<Post> getPostList(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(name = "filters", required = false) String subCategorii) {
        log.info("getPostList route called");

        Page<Post> postList = null;

        Gson gson = new Gson();
        Type listType = new TypeToken<List<SubCategory>>() { }.getType();

        List<SubCategory> subCategories = gson.fromJson(subCategorii, listType);
        if(subCategories != null && subCategories.size() > 0) {
            postList = postBL.getPostListFilteredBySubCategory(page, size, subCategories);
        } else {
            postList = postBL.getPostListOrderedByDate(page, size);
        }


		return postList;
	}

	@PostMapping("/savePost")
	@ResponseBody
	public boolean savePost(HttpServletRequest request, HttpServletResponse response, Principal p) {
		boolean result;

		try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<SubCategory>>() { }.getType();

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            List<SubCategory> subCategories = gson.fromJson(request.getParameter("subCategories"), listType);

            result = postBL.salveazaPostare(title, description, subCategories, p.getName());
        } catch (Exception e) {
		    result = false;
		    e.printStackTrace();
        }

		return result;
	}

	/*@PostMapping(value = {"/savePostXX"})
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
	}*/

}
