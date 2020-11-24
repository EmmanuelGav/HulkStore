package com.hulkstore.service;

import javax.validation.Valid;

import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.User;

public interface UserService {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	User save(@Valid User user);

	void deleteById(Long userId);

	User findByUsername(String username) throws DataNotFoundException;

	User findById(Long id) throws DataNotFoundException;

}
