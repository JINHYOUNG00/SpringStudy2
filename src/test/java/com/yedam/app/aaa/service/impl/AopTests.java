package com.yedam.app.aaa.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.aaa.service.AaaService;

@SpringBootTest
class AopTests {
	
	@Autowired
	AaaService aaaService;
	
	@Test
	@DisplayName("트랜잭션 테스트")
	void transctional() {
		// given
		// when
		aaaService.insert();
		// then
	}

}
