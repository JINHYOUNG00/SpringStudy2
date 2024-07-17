package com.yedam.app.aaa.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.aaa.mapper.AaaMapper;
import com.yedam.app.aaa.service.AaaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AaaServiceImpl implements AaaService {

	private final AaaMapper aaaMapper;
	
	@Transactional
	@Override
	public void insert() {
		aaaMapper.aaaInsert("101");
		aaaMapper.aaaInsert("a101");
	}

}
