package com.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
	
	@Query(value = "SELECT * FROM postari WHERE LOWER(titlu) like %?1%", nativeQuery = true)
	public List<Post> findAllByTitle(String titlu);

	@Query(value = "WITH query AS (SELECT inner_query.*, ROW_NUMBER() OVER (ORDER BY CURRENT_TIMESTAMP) as __hibernate_row_nr__ FROM ( select distinct TOP(?1) a.*\n" +
            "from postari a\n" +
            "inner join mapare_postari_sub_categorii b on a.id = b.id_postare\n" +
            "inner join sub_categorii c on b.id_sub_categorie = c.id  \n" +
            "where c.id in (?2) \n" +
            "order by a.data_adaugare desc \n" +
            ") inner_query ) SELECT * FROM query WHERE __hibernate_row_nr__ >= ?3 AND __hibernate_row_nr__ < ?4",
            nativeQuery = true)
	public List<Post> findAllBySubCategory(int top, List<Long>  v, int start, int end);

}
