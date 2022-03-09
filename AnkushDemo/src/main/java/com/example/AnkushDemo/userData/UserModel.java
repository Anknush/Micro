package com.example.AnkushDemo.userData;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
@Entity
public class UserModel {
	@Id
	@GeneratedValue
	private int id;
	@Size(min=2,message = "Name should we atleast 2 character")
	private String name;
	@Past(message = "Enter date should be in past")
	private Date birthDate;
	@OneToMany(mappedBy = "user")
	private List<Posts> post;
	protected UserModel() {
		
	}
	public UserModel(int id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public List<Posts> getPost() {
		return post;
	}
	public void setPost(List<Posts> post) {
		this.post = post;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
