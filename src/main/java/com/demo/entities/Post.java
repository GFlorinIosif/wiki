package com.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue
	@PrimaryKeyJoinColumn
	private Long id;
	
	@Column(name = "titlu")
	private String titlu;
	
	@Column(name = "descriere")
	private String descriere;
	
	@Column(name = "data_adaugare")
	private Date dataAdaugare;
	
	@ManyToOne
	@JoinColumn(name = "id_autor")
	private User author;
	
	public Post() {
		
	}

	public Post(Long id, String titlu, String descriere, Date dataAdaugare, User user) {
		super();
		this.id = id;
		this.titlu = titlu;
		this.descriere = descriere;
		this.dataAdaugare = dataAdaugare;
		this.author = user;
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
	
}
