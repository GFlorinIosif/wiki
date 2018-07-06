package com.demo.bl;

import com.demo.entities.Category;
import com.demo.entities.Post;
import com.demo.entities.SubCategory;
import com.demo.entities.User;
import com.demo.repositories.PostRepository;
import com.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<Post> getPostListOrderedByDate() {
        Sort sortare = new Sort(Sort.Direction.DESC, "dataAdaugare");

        PageRequest paginare = new PageRequest(0, 10);

        List<Post> postari = postRepo.findAll(sortare);
        return postari;
    }
}
