package com.me.joon.spring.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer>{

	Account findByUserName(String userName);

}
