package com.me.joon.spring.form;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("msg", "Hello from spring boot");
		
		return "index";
	}
	
	@GetMapping("/admin")
	public String admin(Model model, Principal principal) {
		if (principal == null) {
			model.addAttribute("msg", "Hello nonUser");
		} else {
			model.addAttribute("msg", "Hello " + principal.getName());
		}
		
		return "admin";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("msg", "Hello " + principal.getName());
		
		return "dashboard";
	}
	
	@GetMapping("/information")
	public String information(Model model) {
		model.addAttribute("msg", "Hinfomationello from spring boot");
		
		return "information";
	}
}
