package com.moto.safezone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

	
	@GetMapping("/")
	public String getHello() {
		return "hello world";
	}
	@GetMapping("/res")
	public String getRestricted() {
		return "restrincted";
	}
}
