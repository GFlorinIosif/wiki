package com.demo.controller;

import com.demo.entities.Category;
import com.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CategoriiController {

    @Autowired
    CategoryRepository categoryRepo;


    @GetMapping("getListaCategorii")
    @ResponseBody
    public List<Category> getCatList() {
        List<Category> cats = categoryRepo.findAll();
        return cats;
    }



}
