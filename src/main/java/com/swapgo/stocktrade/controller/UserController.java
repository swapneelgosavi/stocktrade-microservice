package com.swapgo.stocktrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swapgo.stocktrade.model.User;
import com.swapgo.stocktrade.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test")
	public String getTest() {
		return "Test Data";
	}
	
	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}
}
