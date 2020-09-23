package com.mode.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class User implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;

	private String name;
	private String username;
	private String email;
	private String password;
	private Date datebirth;
	
	@OneToMany (mappedBy = "user" , fetch = FetchType.EAGER)
	List<Task> tasks = new ArrayList<Task>();
	
	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public User() {
		super();
	}
	
	public User(String name, String username, String email, String password, Date datebirth) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.datebirth = datebirth;
	}
	
	public Long getId_user() {
		return id_user;
	}
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Date getDatebirth() {
		return datebirth;
	}
	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setDatebirth(Date datebirth) {
		this.datebirth = datebirth;
	}

	
}
