package com.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "sub_categorii")
public class SubCategory {
	
	@Id
	@GeneratedValue
	@PrimaryKeyJoinColumn
	private Long id;

	@Column(name = "denumire")
	private String denumire;

	
	public SubCategory() {
		
	}
	
	public SubCategory (long id, String denumire) {
		this.id = id;
		this.denumire = denumire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

}
