package com.example.AnkushDemo.userData;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
@RestController
public class UserController2 {
	@Autowired
	UserDao userdao;
	@Autowired
	UserRepository userRepo;
	@GetMapping("/jpa/users")
	public List<UserModel> allUsers() {
		return userRepo.findAll();
	}
	@GetMapping("/jpa/users/{id}")
	public EntityModel<UserModel> findById(@PathVariable int id) {
		Optional<UserModel> user=userRepo.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found Id: "+id);
		}
		EntityModel<UserModel> reource=EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo=linkTo(methodOn(this.getClass()).allUsers());
		reource.add(linkTo.withRel("all-users"));
		return reource;
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUsers(@PathVariable int id) {
	userRepo.deleteById(id);
//	if(user==null) {
//		throw new UserNotFoundException("User id is not found" +id);
//	}
	}
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserModel user) {
		UserModel savedUser=userRepo.save(user);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}