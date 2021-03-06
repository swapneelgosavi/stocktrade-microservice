package com.swapgo.stocktrade.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
	public ResponseEntity<Object> addShareToUser(@PathVariable int id, @Valid @RequestBody Share share) {
		
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
	
	//this is not working. TODO:
	@DeleteMapping("/user/share/{scriptName}") 
	public void deleteShare(@PathVariable String scriptName) {
		shareRepository.deleteByScriptName(scriptName); 
	}

	
	
	@GetMapping("/user/share")
	public List<Share> getAllShares(){
		return shareRepository.findAll();
	}
	
	//dynamic filtered data : sending id and script name only.
/*	@GetMapping("/filter/user/{id}/share")
	public MappingJacksonValue  getFilterDataOfShare(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID:"+ id);
		}
		Optional<Share> shareOpt= shareRepository.findByUserId(id);
		Share share =  shareOpt.get();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "scriptName");
		FilterProvider filters = new SimpleFilterProvider().addFilter("ShareDataFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(share);

		mapping.setFilters(filters);
		
		return mapping;
	}	*/
	
}
