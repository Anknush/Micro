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
	@Autowired
	PostRepository postRepo;
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
//	Post Controller
	@GetMapping("/jpa/users/{id}/posts")
	public List<Posts> allUsers(@PathVariable int id) {
		Optional<UserModel> userOptional=userRepo.findById(id);
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("id- "+ id);
		}
		return userOptional.get().getPost();
	}
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Posts post) {
		Optional<UserModel> optionalUser=userRepo.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFoundException("id- "+id);
		}
		UserModel user=optionalUser.get();
		post.setUser(user);
		postRepo.save(post);
		URI location=ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
