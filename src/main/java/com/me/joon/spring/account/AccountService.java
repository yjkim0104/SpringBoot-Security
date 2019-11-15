package com.me.joon.spring.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService{

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Account account = accountRepository.findByUserName(userName);
		if (account == null) {
			throw new UsernameNotFoundException(userName);
		}
		
		return User.builder()
				.username(account.getUserName())
				.password(account.getPassword())
				.roles(account.getRole())
				.build();
	}

	public Account createUser(Account account) {
		account.encodePassword(passwordEncoder);
		return accountRepository.save(account);
	}

}
