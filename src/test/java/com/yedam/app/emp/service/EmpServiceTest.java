package com.yedam.app.emp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;

@SpringBootTest
public class EmpServiceTest {
	
	@Autowired EmpMapper empMapper;
	
	@Test
	@DisplayName("empMapper 테스트")
	void isNotNullEmpMapperTest() {
		// given
		// when
		
		// then
		assertNotNull(empMapper);
	}
	
	@Test
	@DisplayName("사원리스트 테스트")
	void selectEmpAllTest() {
		// given
		List<EmpVO> selectEmpAll = empMapper.selectEmpAll();
		// when
		
		// then
		assertTrue(!selectEmpAll.isEmpty());
	}
	
	@Test
	@DisplayName("단건조회 테스트")
	void selectEmpInfoTest() {
		// given
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(114);
		// when
		EmpVO selectEmpInfo = empMapper.selectEmpInfo(empVO);
		// then
		assertEquals(selectEmpInfo.getEmpname(), "Den");
	}
	
	@Test
	@DisplayName("사원등록 테스트")
	void insertEmpInfoTest() {
		// given
		EmpVO empVO = new EmpVO();
//		empVO.setEmpid(654);
		empVO.setEmpname("hong");
		empVO.setMgr(100);
		empVO.setSal(10000);
		// when
		int insertEmpInfo = empMapper.insertEmpInfo(empVO);
		// then
		assertEquals(empVO.getEmpid(), 656);
		
	}
	
//	@Test
	@DisplayName("사원정보수정")
	void updateEmpInfoTest() {
		// given
		// 1. 단건조회 => 업데이트
		EmpVO empVO = new EmpVO();
		empVO.setEmpid(1);
		
		// when
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		findVO.setEmpname("Kang");
		findVO.setMgr(114);
		int result = empMapper.updateEmpInfo(findVO.getEmpid(), findVO);

		// then
		assertEquals(1, result);
	}
	
//	@Test
	@DisplayName("사원삭제")
	void deleteEmpTest() {
		// given
		int result = empMapper.deleteEmp(1);
		// when

		// then
		assertEquals(1, result);
	}
	
}
