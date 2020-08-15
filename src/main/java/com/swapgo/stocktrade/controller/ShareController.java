package com.swapgo.stocktrade.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class ShareController {

	@Autowired
	ShareRepository shareRepository;
	
	@Autowired
	private UserRepository userRepository;	
	
	
	@GetMapping("/user/{id}/share")
	public Share getUserShare(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID:"+ id);
		}
		Optional<Share> share= shareRepository.findByUserId(id);
		return share.get();
	}
	
	@PostMapping("/user/{id}/addshare")
	public ResponseEntity<Object> addShareToUser(@PathVariable int id,  @RequestBody Share share) {
		
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User does not exist:"+ share.getUser().getId());
		}		
		
		share.setUser(user.get());
		
		Share savedShare = shareRepository.save(share);
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedShare.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}	
	
	/*
	 * @DeleteMapping("/user/share/{scriptName}") public void
	 * deleteShare(@PathVariable String scriptName) {
	 * shareRepository.deleteByScriptName(scriptName); }
	 */
	
	
	@GetMapping("/user/share")
	public List<Share> getAllShares(){
		return shareRepository.findAll();
	}
	
	
	
}
