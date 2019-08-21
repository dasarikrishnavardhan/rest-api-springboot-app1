/**
 * 
 */
package com.krishnadasari.springboot.restapi.app1.restapispringbootapp1.usermgmt.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishnadasari.springboot.restapi.app1.restapispringbootapp1.usermgmt.model.User;

/**
 * @author dasari krishna vardhan
 *
 */
@RestController
@RequestMapping(path = "/users")
public class UserController 
{
	private static Set<User> users = new HashSet<User>();
	
	private static int USER_COUTNT = 0; 
	
	
	@GetMapping
	public Set<User> getAllUsers()
	{
		return users;
	}
	
	@GetMapping(path = "/{userId}")
	public User getUser(@PathVariable("userId") Integer userId)
	{
		return getUserById(userId);
	}
	
	@PostMapping	
	public User addUser(@RequestBody User newUser)
	{
		USER_COUTNT = USER_COUTNT +1;
		newUser.setId(USER_COUTNT);
		users.add(newUser);
		return newUser;
	}
	
	@PutMapping(path = "/{userId}")
	public User updateUser(@PathVariable("userId") Integer userId,@RequestBody User updatedUser)
	{
		User existingUser =  getUserById(userId);
		
		existingUser.setFirstName(updatedUser.getFirstName());
		existingUser.setLastName(updatedUser.getLastName());
		existingUser.setEmail(updatedUser.getEmail());
		users.add(existingUser);
		
		return existingUser;
	}
	
	@DeleteMapping(path = "/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId)
	{
		User existingUser =  getUserById(userId);
		users.remove(existingUser);
		return "UserDeleted Successfully ";
	}
	
	private User getUserById(Integer userId)
	{
		User result = null;
		for(User user : users)
		{
			if(user.getId().equals(userId))
			{
				result = user;
			}
		}
		
		return result;
	}
}
