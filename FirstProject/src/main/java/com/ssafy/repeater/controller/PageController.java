package com.ssafy.repeater.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

	@GetMapping("/home123")
	public String goHome() {
		System.out.println("HIHIHI");
		return "home.html";
	}
}
