package com.moto.safezone.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class CheckController {

	
	@GetMapping("/")
	public String getHello() {
		return "hello world";
	}

	@GetMapping("/res")
	public String getRestricted() {
		return "restrincted get";
	}

	@GetMapping("/res-pos")
	public String getRestrictedPost() {
		return "restrincted post";
	}

}
