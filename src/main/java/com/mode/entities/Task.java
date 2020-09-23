package com.mode.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Task implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_task;
	private String category;
	private String title;
	private String label;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn (name = "id_user")
	private User user;
	
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Task(String category, String title, String label) {
		super();
		this.category = category;
		this.title = title;
		this.label = label;
	}
	
	
	public Long getId_task() {
		return id_task;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public String getLabel() {
		return label;
	}

	public void setId_task(Long id_task) {
		this.id_task = id_task;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLabel(String label) {
		this.label = label;
	}


	
	

}
