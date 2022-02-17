package com.example.AnkushDemo.userData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
	@DeleteMapping("/users/{id}")
	public void deleteUsers(@PathVariable int id) {
	UserModel user=userdao.deleteUser(id);
	if(user==null) {
		throw new UserNotFoundException("User id is not found" +id);
	}
	}
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody UserModel user) {
		UserModel savedUser=userdao.saveUser(user);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}