package com.me.joon.spring.account;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	void index_anoymous() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
		;
	}

	@Test
	@WithUser
	void index_user() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk())
		;
	}
	
	@Test
	@WithUser
	void admin_user() throws Exception {
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isForbidden())
		;
	}
	
	@Test
	@WithMockUser(username = "joonAdminr", roles = "ADMIN")
	void admin_admin() throws Exception {
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isOk())
			;
	}
}
