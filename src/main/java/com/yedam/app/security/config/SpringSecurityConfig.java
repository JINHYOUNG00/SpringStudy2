package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	// 1. 시큐리티를 설정할때 가장 먼저해야 할일은 암호화/복호화 방식 Bean 등록해줘야함
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 2. 시큐리티가 인증/인가 할 설정
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()// 시큐리티가 체크해야하는 경로 및 각 경로별 권한
//				.antMatchers("/empList") // 경로
//				.hasRole("USER") // 권한
//				.anyRequest().authenticated(); // 권한은 무시하고 인증될때만 검증해서 접근 허용
		
		// 포괄적인 부분은 밑에 있어야함
				.antMatchers("/", "/all") 		// 경로
					.permitAll()				// 권한(인증 인가 확인하지않음)
				.antMatchers("/admin/**") 		// 경로
					.hasRole("ADMIN") 			// 권한 -> ROLE_ADMIN
				.antMatchers("/user/**") 		// 경로
//					.hasAuthority("ROLE_USER") 	// 권한 -> 이렇게 했을때 관리자에서 유저쪽 페이지 접근할 수 없다.
					.hasAnyAuthority("ROLE_USER", "ROLE_ADMIN") // 이 중 하나라도 권한이 있다면 접근 가능하도록
				.anyRequest()
					.authenticated()			// 권한은 무시하고 인증될때만 검증해서 접근 허용
			.and()
			.formLogin()
				.defaultSuccessUrl("/all")
			.and()
			.logout()
				.logoutSuccessUrl("/all"); // 로그아웃을 했을때 어떤 페이지로 이동할지 설정 안했을 시 디폴트는 루트
		
//		http.csrf().disable(); // csrf 비활성화
		
		return http.build();
	}
	// 테스트용  메모리 인증 방식
//	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailsService() {
		UserDetails user = User.builder()
								.username("user1")
								.password(passwordEncoder().encode("1234")) // 패스워드 암호화 작업도 포함
//								.authorities("ROLE_USER")
								.roles("USER") // ROLE_USER
								.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234")) // 패스워드 암호화 작업도 포함
								.authorities("ROLE_ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
}

