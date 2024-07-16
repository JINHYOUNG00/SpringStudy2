package com.yedam.app.dept.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.dept.mapper.DeptMapper;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeptServiceTest {
	
	@Autowired DeptMapper deptMapper;
	
	@BeforeEach
	void before() {
		
	}

	@Test
	@Order(1)
	@DisplayName("deptMapper 테스트")
	void isNotNullDeptMapperTest() {
		// given

		// when

		// then
		assertNotNull(deptMapper);;
		
	}
	
	@Test
	@Order(2)
	@DisplayName("부서리스트 테스트")
	void selectDeptAllTest() {
		// given
		List<DeptVO> selectDeptAll = deptMapper.selectDeptAll();
		// when
//		log.info("vo={}",selectDeptAll);
		// then
		assertTrue(!selectDeptAll.isEmpty());
	}
	
	@Test
	@Order(3)
	@DisplayName("단건조회 테스트")
	void selectDeptInfoTest() {
		// given
//		DeptVO deptVO = new DeptVO();
//		deptVO.setDepartmentId(10);
		// when
		DeptVO selectDeptInfo = deptMapper.selectDeptInfo(10L);
		// then
		assertEquals(selectDeptInfo.getDepartmentName(), "Administration");
	}
	
	@Test
	@Order(4)
	@DisplayName("부서등록 테스트")
	void insertDeptInfoTest() {
		// given
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartmentName("Dev");
		deptVO.setLocationId(1700);
		// when
		deptMapper.insertDeptInfo(deptVO);
		// then
		assertEquals(deptVO.getDepartmentId(), 271);
	}
	
	@Test
	@Order(5)
	@DisplayName("부서정보수정테스트")
	void UpdateDeptInfo() {
		// given
//		DeptVO deptVO = new DeptVO();
//		deptVO.setDepartmentId(271);
		// when
		DeptVO findVO = deptMapper.selectDeptInfo(271L);
		findVO.setDepartmentName("Developer");
		int result = deptMapper.updateDeptInfo(findVO.getDepartmentId(), findVO);
		// then
		assertEquals(1, result);
	}
	
	@Test
	@Order(6)
	@DisplayName("부서정보삭제테스트")
	void deleteDept() {
		// given
		int result = deptMapper.deleteDept(271);
		// then
		assertEquals(1, result);
	}
}
