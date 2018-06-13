package com.demo.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "idAutor")
	private Long idAutor;
	
	@Column(name = "idPostare")
	private Long idPostare;
	
	@Column(name = "comentariu")
	private String comentariu;
	
	@Column(name = "dataAdaugare")
	private Date dataAdaugare;
	
	public Comment() {
		
	}

	public Comment(Long id, Long idAutor, Long idPostare, String comentariu, Date dataAdaugare) {
		super();
		this.id = id;
		this.idAutor = idAutor;
		this.idPostare = idPostare;
		this.comentariu = comentariu;
		this.dataAdaugare = dataAdaugare;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

	public Long getIdPostare() {
		return idPostare;
	}

	public void setIdPostare(Long idPostare) {
		this.idPostare = idPostare;
	}

	public String getComentariu() {
		return comentariu;
	}

	public void setComentariu(String comentariu) {
		this.comentariu = comentariu;
	}

	public Date getDataAdaugare() {
		return dataAdaugare;
	}

	public void setDataAdaugare(Date dataAdaugare) {
		this.dataAdaugare = dataAdaugare;
	}
	
	

}
