package com.demo.bl;

import com.demo.entities.Post;
import com.demo.entities.SubCategory;
import com.demo.entities.User;
import com.demo.repositories.PostRepository;
import com.demo.repositories.UserRepository;
import com.demo.repositories.implementations.PostRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostBL {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepositoryImpl postRepository2;


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
        List<Long> ids = new ArrayList<>();
        for(SubCategory subCat : subCategori) {
            ids.add(Long.valueOf(Math.toIntExact(subCat.getId())));
        }
        Page<Post> postari = new PageImpl<>(postRepository2.findAllBySubCategoryAndPaginate(page * size, size , ids), new PageRequest(page, size), postRepository2.countAllBySubCategory(ids));
        return postari;
    }

    public Page<Post> getPostListByTitle(String title, int page, int size) {
        return new PageImpl<Post>(postRepository2.findAllByTitleAndPaginate(page * size, size, title), new PageRequest(page, size), postRepository2.countAllByTitle(title));
    }

    public Page<Post> getPostListByAuthor(String autor, int page, int size) {
        return new PageImpl<Post>(postRepository2.findAllByAuthorAndPaginate(page * size, size, autor), new PageRequest(page, size), postRepository2.countAllByAuthor(autor));
    }


}
