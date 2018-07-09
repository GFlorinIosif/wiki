package com.demo.repositories.implementations;

import com.demo.entities.Post;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
public class PostRepositoryImpl {
    
    @PersistenceContext
    private EntityManager em;

    public List<Post> findAllBySubCategoryAndPaginate( int start, int end, List<Long>  ids){
        Session session = em.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Post.class)
                .createAlias("subCategorii", "subcategorie")
                .add(Restrictions.in("subcategorie.id", ids))
                .addOrder(Order.desc("dataAdaugare"))
                .setFirstResult(start)
                .setMaxResults(end);

        return criteria.list();
    }

    public Long countAllBySubCategory(List<Long>  ids){
        String sql = "Select count(p.id) from Post p inner join p.subCategorii c where c.id in (:ids)";

        return (Long) em.createQuery(sql).setParameter("ids", ids).getSingleResult();
    }

    public List<Post> findAllByTitleAndPaginate(int start, int end, String titlu){
        Session session = em.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Post.class)
                .add(Restrictions.like("titlu", "%" + titlu+ "%"))
                .addOrder(Order.desc("dataAdaugare"))
                .setFirstResult(start)
                .setMaxResults(end);

        return criteria.list();
    }

    public Long countAllByTitle(String titlu){
        String sql = "Select count(p.id) from Post p where p.titlu like :titlu";

        return (Long) em.createQuery(sql).setParameter("titlu", "%" + titlu + "%").getSingleResult();
    }

    public List<Post> findAllByAuthorAndPaginate( int start, int end, String autor){
        Session session = em.unwrap(Session.class);

        Criteria criteria = session.createCriteria(Post.class)
                .createAlias("author", "aut")
                .add(Restrictions.like("aut.nume", "%" + autor + "%"))
                .addOrder(Order.desc("dataAdaugare"))
                .setFirstResult(start)
                .setMaxResults(end);

        return criteria.list();
    }

    public Long countAllByAuthor(String autor){
        String sql = "Select count(p.id) from Post p inner join p.author c where c.nume like :autor";

        return (Long) em.createQuery(sql).setParameter("autor", "%" + autor + "%").getSingleResult();
    }
}
