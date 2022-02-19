package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;//sale
import org.springframework.http.ResponseEntity;//sale
import org.springframework.web.bind.annotation.DeleteMapping;//sale
import org.springframework.web.bind.annotation.GetMapping;//sale
import org.springframework.web.bind.annotation.PathVariable;//sale
import org.springframework.web.bind.annotation.PostMapping;//sale
import org.springframework.web.bind.annotation.PutMapping;//sale
import org.springframework.web.bind.annotation.RequestBody;//sale
import org.springframework.web.bind.annotation.RequestMapping;//sale
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;//sale

import com.example.demo.caseUse.CreateUser;
import com.example.demo.caseUse.DeleteUser;
import com.example.demo.caseUse.GetUser;
import com.example.demo.caseUse.UpdateUser;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping ("/api/users")
public class UserRestController {
	//crete, get, delete, update
	private GetUser getUser;
	private CreateUser createUser;
	private DeleteUser deleteUser;
	private UpdateUser updateUser;
	private UserRepository userRepository;
	
	public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, UserRepository userRepository) {
		this.getUser = getUser;
		this.createUser = createUser;
		this.deleteUser = deleteUser;
		this.updateUser = updateUser;
		this.userRepository = userRepository;
	}
	
	@GetMapping ("/")
	List<User> get(){
		return getUser.getAll();
	}
	
	//create
	@PostMapping ("/")
	ResponseEntity<User> newUser(@RequestBody User newUser){
		return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
	}
	
	//delete
	@DeleteMapping ("/{id}")
	ResponseEntity deleteUser(@PathVariable Long id) {
		deleteUser.remove(id);
	return new ResponseEntity(HttpStatus.NO_CONTENT);	
	}
	
	//update
	@PutMapping ("/{id}")
	ResponseEntity <User> replaceUser (@RequestBody User newUser , @PathVariable Long id){
		return new ResponseEntity<> (updateUser.update(newUser, id), HttpStatus.OK);
	}
	
	@GetMapping ("/pageable")
	List<User> getUserPageable(@RequestParam int page, @RequestParam int size){
		return userRepository.findAll(PageRequest.of(page, size)).getContent();
	}

}
