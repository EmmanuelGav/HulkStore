package com.hulkstore.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hulkstore.exceptions.DataNotFoundException;
import com.hulkstore.models.User;
import com.hulkstore.repositories.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
 
    @Mock
    private UserRepository userRepository;
 
    private String username;
	private String email;
	private User user;
	private Optional<User> userOptional;

    @Before
    public void init() {
    	username = "emmanuel.gaviria";
    	email = "emmanuel.gaviria@gmail.com";

		user = new User();
		userOptional = Optional.of(user);
    }
	
    @Test
    public void existsByUsername() {
        when(userRepository.existsByUsername(username)).thenReturn(true);
        assertEquals(userService.existsByUsername(username), true);
    }
 
    @Test
    public void notExistsByUsername() {
        when(userRepository.existsByUsername(username)).thenReturn(false);
        assertEquals(userService.existsByUsername(username), false);
    }

    @Test
    public void existsByEmail() {
        when(userRepository.existsByEmail(email)).thenReturn(true);
        assertEquals(userService.existsByEmail(email), true);
    }
    
    @Test
    public void notExistsByEmail() {
        when(userRepository.existsByEmail(email)).thenReturn(false);
        assertEquals(userService.existsByEmail(email), false);
    }

    @Test
	public void save() {
    	when(userRepository.save(user)).thenReturn(user);
        assertEquals(userService.save(user), user);
	}

    @Test
	public void deleteById() {
    	doNothing().when(userRepository).deleteById(1L);
        userService.deleteById(1L);
	}

	@Test
	public void findByUsername() throws DataNotFoundException {
    	when(userRepository.findByUsername(username)).thenReturn(userOptional);
        assertEquals(userService.findByUsername(username), user);
        
	}
	
//	@Test(expected = DataNotFoundException.class)
//	public void findByUsernameNotFound() throws DataNotFoundException {
//    	when(userRepository.findByUsername(username)).thenThrow(DataNotFoundException.class);
//        userService.findByUsername(username);
//	}

	@Test
	public void findById() throws DataNotFoundException {
    	when(userRepository.findById(1L)).thenReturn(userOptional);
        assertEquals(userService.findById(1L), user);
	}
	
//	@Test(expected = DataNotFoundException.class)
//	public void findByIdNotFound() throws DataNotFoundException {
//    	when(userRepository.findByUsername(username)).thenThrow(DataNotFoundException.class);
//    	userRepository.findByUsername(username);
//	}

}
