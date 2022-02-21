package com.example.AnkushDemo.userData;
import java.util.*;

import javax.persistence.Entity;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
@Entity
public class UserModel {
	private int id;
	@Size(min=2,message = "Name should we atleast 2 character")
	private String name;
	@Past(message = "Enter date should be in past")
	private Date birthDate;
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
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
}
