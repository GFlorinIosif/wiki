package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.entities.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query(value = "SELECT * FROM post WHERE LOWER(titlu) like %?1%", nativeQuery = true)
	public List<Post> findAllByTitle(String titlu);
	
	@Query(value = "SELECT * FROM post ORDER BY data_adaugare desc", nativeQuery = true)
	public List<Post> findAllOrderedByDate();

}
