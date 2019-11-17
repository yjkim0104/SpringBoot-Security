package com.me.joon.spring.account;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.security.test.context.support.WithMockUser;

@Retention(RUNTIME)
@WithMockUser(username = "joonAdminr", roles = "ADMIN")
public @interface WithUser {

}
