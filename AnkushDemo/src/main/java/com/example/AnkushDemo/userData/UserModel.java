package com.example.AnkushDemo.userData;
import java.util.*;

import javax.persistence.Entity;
@Entity
public class UserModel {
	private int id;
	private String name;
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
