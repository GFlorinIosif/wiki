package com.demo.entities;

import jdk.nashorn.internal.objects.annotations.Property;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "postari")
public class Post {
	
	@Id
	@GeneratedValue
	@PrimaryKeyJoinColumn
	private Long id;
	
	@Column(name = "titlu")
	private String titlu;
	
	@Column(name = "descriere")
	private String descriere;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_adaugare")
	private Date dataAdaugare = new Date();
	
	@ManyToOne
	@JoinColumn(name = "id_autor")
	private User author;

	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "mapare_postari_fisiere", joinColumns = {@JoinColumn(name = "id_postare")}, inverseJoinColumns = {@JoinColumn(name = "id_fisier")})
	private List<File> files;
	
	@OneToMany
	@JoinTable(name = "mapare_postari_sub_categorii", joinColumns = {@JoinColumn(name = "id_postare")}, inverseJoinColumns = {@JoinColumn(name = "id_sub_categorie")})
	private List<SubCategory> subCategorii;
	
	public Post() {
		
	}

	public Post(Long id, String titlu, String descriere, Date dataAdaugare, User user, List<File> files, List<SubCategory> subCategorii) {
		super();
		this.id = id;
		this.titlu = titlu;
		this.descriere = descriere;
		this.dataAdaugare = dataAdaugare;
		this.author = user;
		this.files = files;
		this.subCategorii = subCategorii;
	}

	public Long getId() {
		return id;
	}

	public String getTitlu() {
		return titlu;
	}

	public void setTitlu(String titlu) {
		this.titlu = titlu;
	}

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	public Date getDataAdaugare() {
		return dataAdaugare;
	}

	public void setDataAdaugare(Date dataAdaugare) {
		this.dataAdaugare = dataAdaugare;
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public List<File> getfiles() {
		return files;
	}
	
	public void setFiles(List<File> files) {
		this.files = files;
	}

	public List<SubCategory> getSubCategori() {
		return subCategorii;
	}

	public void setSubCategori(List<SubCategory> subCategorii) {
		this.subCategorii = subCategorii;
	}
	
	
}
