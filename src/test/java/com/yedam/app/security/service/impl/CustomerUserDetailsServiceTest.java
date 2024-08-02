package com.yedam.app.security.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class CustomerUserDetailsServiceTest {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	@DisplayName("스프링 시큐리티 테스트")
	void 메서드명() {
		String password = "1234"; // 사용자가 입력
		
		//DB에 저장된 암호화된 비밀번호
		String enPwd = passwordEncoder.encode(password);
		System.out.println("enPwd : " + enPwd);
		
		boolean matchResult = passwordEncoder.matches(password, enPwd);
		System.out.println("match : " + matchResult);
	
	}

}
