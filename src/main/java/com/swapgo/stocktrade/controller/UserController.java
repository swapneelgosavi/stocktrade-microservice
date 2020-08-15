package com.swapgo.stocktrade.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.swapgo.stocktrade.exception.UserNotFoundException;
import com.swapgo.stocktrade.model.Share;
import com.swapgo.stocktrade.model.User;
import com.swapgo.stocktrade.repository.ShareRepository;
import com.swapgo.stocktrade.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ShareRepository shareRepository;
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID:"+ id);
		}
		
		return user.get();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		
		Optional<Share> share = shareRepository.findByUserId(id);
		
		if(share.isPresent()) {
			shareRepository.deleteById(share.get().getId());
		}
		userRepository.deleteById(id);
	}

	
	@PostMapping("/adduser")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}	
}
