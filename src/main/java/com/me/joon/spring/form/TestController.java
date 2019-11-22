package com.me.joon.spring.form;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.me.joon.spring.account.Account;
import com.me.joon.spring.account.AccountContext;
import com.me.joon.spring.account.AccountRepository;

@Controller
public class TestController {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TestService testService;
	
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
		AccountContext.setAccount(accountRepository.findByUserName(principal.getName()));
		testService.dashboard();
		return "dashboard";
	}
	
	@GetMapping("/information")
	public String information(Model model) {
		model.addAttribute("msg", "information from spring boot");
		return "information";
	}
}
