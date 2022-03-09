package com.example.AnkushDemo.userData;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Posts {
	@Id
	@GeneratedValue
	private int Id;
	private String Description;
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private UserModel user;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Posts [Id=" + Id + ", Description=" + Description + "]";
	}
	
}
