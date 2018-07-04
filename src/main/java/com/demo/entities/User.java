package com.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable  {
	
	@Id
	@GeneratedValue
	@PrimaryKeyJoinColumn
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "nume")
	private String nume;
	
	@Column(name = "prenume")
	private String prenume;
	
	@Column(name = "rol")
	private String rol;
	
	public User() {
		
	}
	
	public User(Long id, String username, String password, String nume, String prenume, String rol) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nume = nume;
		this.prenume = prenume;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	

}
