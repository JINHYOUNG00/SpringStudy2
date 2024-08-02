package com.yedam.app.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginUserVO implements UserDetails{
	
	private UserVO userVO;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority(userVO.getRoleName()));
		
		return auths;
	}

	@Override
	public String getPassword() {
		return userVO.getPassword();
	}

	@Override
	public String getUsername() {
		return userVO.getLoginId();
	}

	// 계정만료 여부 체크
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	// 계정 잠금 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	// 계정 패스워드 잠금 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	// 계정 사용 여부
	@Override
	public boolean isEnabled() {
		return true;
	}

}
