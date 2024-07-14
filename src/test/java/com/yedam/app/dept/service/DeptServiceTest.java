package com.yedam.app.dept.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.dept.mapper.DeptMapper;

@SpringBootTest
public class DeptServiceTest {
	
	@Autowired DeptMapper deptMapper;

	@Test
	@DisplayName("deptMapper 테스트")
	void isNotNullDeptMapperTest() {
		// given

		// when

		// then
		Assertions.assertNotNull(deptMapper);;
		
	}
	
	@Test
	@DisplayName("부서리스트 테스트")
	void selectDeptAllTest() {
		// given
		List<DeptVO> selectDeptAll = deptMapper.selectDeptAll();
		// when

		// then
		Assertions.assertTrue(!selectDeptAll.isEmpty());
	}
	
	@Test
	@DisplayName("단건조회 테스트")
	void selectDeptInfoTest() {
		// given
//		DeptVO deptVO = new DeptVO();
//		deptVO.setDepartmentId(10);
		// when
		DeptVO selectDeptInfo = deptMapper.selectDeptInfo(10L);
		// then
		Assertions.assertEquals(selectDeptInfo.getDepartmentName(), "Administration");
	}
	
	@Test
	@DisplayName("부서등록 테스트")
	void insertDeptInfoTest() {
		// given
		DeptVO deptVO = new DeptVO();
		deptVO.setDepartmentName("Dev");
		deptVO.setLocationId(1700);
		// when
		deptMapper.insertDeptInfo(deptVO);
		// then
		Assertions.assertEquals(deptVO.getDepartmentId(), 271);
	}
	
	@Test
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
		Assertions.assertEquals(1, result);
	}
	
	@Test
	@DisplayName("부서정보삭제테스트")
	void deleteDept() {
		// given
		int result = deptMapper.deleteDept(271);
		// then
		Assertions.assertEquals(1, result);
	}
}
