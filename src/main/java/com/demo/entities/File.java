package com.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {
	
	@Id
	@GeneratedValue
	@PrimaryKeyJoinColumn
	private Long id;
	
	@Column(name = "nume")
	private String nume;
	
	@Column(name = "tip_continut")
	private String tipContinut;
	
	@Column(name = "dimensiune")
	private Long dimensiune;
	
	@Column(name = "cale")
	private String cale;
	
	@Column(name = "guid")
	private String guid;
	
	public File() { }
	
	public File(long id, String nume, String tipContinut, long dimensiune, String cale, String guid) {
		this.id = id;
		this.nume = nume;
		this.tipContinut = tipContinut;
		this.dimensiune = dimensiune;
		this.cale = cale;
		this.guid = guid;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getTipContinut() {
		return tipContinut;
	}

	public void setTipContinut(String tipContinut) {
		this.tipContinut = tipContinut;
	}

	public Long getDimensiune() {
		return dimensiune;
	}

	public void setDimensiune(Long dimensiune) {
		this.dimensiune = dimensiune;
	}

	public String getCale() {
		return cale;
	}

	public void setCale(String cale) {
		this.cale = cale;
	}
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
