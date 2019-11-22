package com.me.joon.spring.form;

import org.springframework.stereotype.Service;

import com.me.joon.spring.account.AccountContext;

@Service
public class TestService {

	public void dashboard() {
		
		System.out.printf("## Accoutcontext getUserName() : [%s]", AccountContext.getAccount().getUserName());
		
	}
}
