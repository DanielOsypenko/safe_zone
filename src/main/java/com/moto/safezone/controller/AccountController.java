package com.moto.safezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.safezone.dto.EditUserDto;
import com.moto.safezone.dto.UserDto;
import com.moto.safezone.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	UserService userService;
	

	@PostMapping("/register/floor/{floor}/place/{place}")
	public UserDto userRegister(@PathVariable("floor") Integer floor, @PathVariable("place") Integer place) {
		return userService.registerUser(floor, place);
	}
	
	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable("id") String id) {
		return userService.findUser(id);
	}
	
	@PutMapping("/edit/name")
	public UserDto editUserName(@RequestBody EditUserDto editUserDto) {
		return userService.editUser(editUserDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
