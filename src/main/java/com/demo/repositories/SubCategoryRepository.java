package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

}
