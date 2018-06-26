package com.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
