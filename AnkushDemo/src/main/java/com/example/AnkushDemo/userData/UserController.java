package com.example.AnkushDemo.userData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
@RestController
public class UserController {
	@Autowired
	UserDao userdao;
	@GetMapping("/users")
	public List<UserModel> allUsers() {
		return userdao.findAll();
	}
	@GetMapping("/users/{id}")
	public UserModel findById(@PathVariable int id) {
		UserModel user=userdao.findOne(id);
		if(user.getId()==0) {
			throw new UserNotFoundException("User not found Id: "+id);
		}
		return user;
	}
}
