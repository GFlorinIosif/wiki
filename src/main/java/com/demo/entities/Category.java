package com.demo.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorii")
public class Category {
	
	@Id
	@GeneratedValue
	@PrimaryKeyJoinColumn
	private Long id;
	
	@Column(name = "denumire")
	private String denumire;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mapare_cat_sub_cat", joinColumns = {@JoinColumn(name = "id_categorie")}, inverseJoinColumns = {@JoinColumn(name = "id_sub_categorie")})
	List<SubCategory> subCategorii;

	public Category() {
		
	}
	
	public Category (long id, String denumire) {
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

	public List<SubCategory> getSubCategorii () { return subCategorii; }

	public void setSubCategorii(List<SubCategory> subCategorii) { this.subCategorii = subCategorii; }

}
