package com.hulkstore.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.User;
import com.hulkstore.repositories.UserRepository;
import com.hulkstore.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Transactional
	@Override
	public User save(@Valid User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User findByUsername(String username) throws DataNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new DataNotFoundException("Error: Username is not found."));
	}

	@Override
	public User findById(Long id) throws DataNotFoundException {
		return userRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("Error: User is not found."));
	}


}
