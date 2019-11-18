package com.me.joon.spring.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private AccountService accountService;
	
	private static String userName = "joonUser";
	private static String password = "123";
	
	
	@Test
	@Order(1)
	public void a1_AddUser() {
		System.out.println("### a1_AddUser test");
		
		Account account = new Account();
		account.setUserName(userName);
		account.setPassword(password);
		account.setRole("USER");
		
		accountService.createUser(account);
	}
	
	@Test
	@Order(2)
	@WithAnonymousUser
	public void index_anoymous() throws Exception {
		System.out.println("### index_anoymous test");
		
		mockMvc.perform(get("/"))
//			.andDo(print())
			.andExpect(status().isOk())
		;
	}

	@Test
	@Order(2)
	@WithUser
	public void index_user() throws Exception {
		System.out.println("### index_user test");
		
		mockMvc.perform(get("/"))
//			.andDo(print())
			.andExpect(status().isOk())
		;
	}
	
	@Test
	@WithUser
	@Order(2)
	public void admin_user() throws Exception {
		System.out.println("### admin_user test");
		
		mockMvc.perform(get("/admin"))
//			.andDo(print())
			.andExpect(status().isForbidden())
		;
	}
	
	@Test
	@Order(2)
	@WithMockUser(username = "joonAdmin", roles = "ADMIN")
	public void admin_admin() throws Exception {
		System.out.println("### admin_admin test");
		mockMvc.perform(get("/admin"))
//			.andDo(print())
			.andExpect(status().isOk())
		;
	}
	
	@Test
	@Order(2)
	public void login() throws Exception{
		System.out.println("### login test");
		Account user = accountService.selectOneUser(userName);
		mockMvc.perform(formLogin().user(user.getUserName()).password(password))
				.andExpect(authenticated())
		;
				
	}
	
	@Test
	@Order(3)
	public void login_fail() throws Exception{
		System.out.println("### login_fail test");
		Account user = accountService.selectOneUser(userName);
		mockMvc.perform(formLogin().user(user.getUserName()).password("wrongpwd"))
				.andExpect(unauthenticated())
		;
				
	}
	
}
