package com.demo.bl;

import com.demo.entities.Category;
import com.demo.entities.Post;
import com.demo.entities.SubCategory;
import com.demo.entities.User;
import com.demo.repositories.PostRepository;
import com.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import javax.persistence.OrderBy;
import java.util.List;

@Component
public class PostBL {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;


    public boolean salveazaPostare(String title, String description, List<SubCategory> subCategories, String autorUserName) throws Exception {
        boolean postSaved = true;

        User autor = userRepo.findByUserName(autorUserName);

        Post postare = new Post();
        postare.setTitlu(title);
        postare.setDescriere(description);
        postare.setSubCategori(subCategories);
        postare.setAuthor(autor);

        Post savedPost = postRepo.save(postare);
        if (null == savedPost) {
            postSaved = false;
        }

        return postSaved;
    }

    public Page<Post> getPostListOrderedByDate(int page, int size) {
        Sort sortare = new Sort(Sort.Direction.DESC, "dataAdaugare");
        Pageable p = new PageRequest(page, size, sortare);
        Page<Post> postari = postRepo.findAll(p);
        return postari;
    }

    public Page<Post> getPostListFilteredBySubCategory(int page, int size, List<SubCategory> subCategori) {
        Sort sortare = new Sort(Sort.Direction.DESC, "dataAdaugare");
        Pageable p = new PageRequest(page, size, sortare);
        String ids = "";
        for(SubCategory subCat : subCategori) {
            ids += subCat.getId() + ",";
        }
        ids = ids.substring(0, ids.length() - 1);

        Page<Post> postari;
        System.out.println("LIMITS : " + page + " ->  size " + size + " => " + (page * size + 1) + " -> " + (page * size + size + 1));
        List<Post> postList = postRepo.findAllBySubCategory(Integer.MAX_VALUE, ids, 0, 100);
        postari = new PageImpl<Post>(postRepo.findAllBySubCategory(Integer.MAX_VALUE, ids, page * size + 1, page * size + size + 1), p, postList.size());


        return postari;
    }

}
