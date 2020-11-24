package com.hulkstore.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.User;
import com.hulkstore.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<User> save(@Valid @RequestBody User user) throws DataNotFoundException {
		
		User userExist = userService.findByUsername(user.getUsername());
		userExist.setFirstName(user.getFirstName());
		userExist.setLastName(user.getLastName());
		userExist.setPhoneNumber(user.getPhoneNumber());
		userExist.setCity(user.getCity());
		userExist.setAddress(user.getAddress());
		
		User response = userService.save(userExist);
		
		return ResponseEntity.ok(response);
	}

	@GetMapping("/findById/{id}")
//	@PreAuthorize("hasAuthority('User') or hasAuthority('Admin') or hasAuthority('SuperAdmin')")
	public ResponseEntity<User> findById(@PathVariable Long id) throws DataNotFoundException {
		
		User userExist = userService.findById(id);
		
		return ResponseEntity.ok(userExist);
	}

}