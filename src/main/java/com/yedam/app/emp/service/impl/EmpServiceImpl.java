package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service // AOP가 적용될 유일한 Bean
public class EmpServiceImpl implements EmpService{

	@Autowired // 필드 주입
	EmpMapper empMapper;
	
	@Override
	public List<EmpVO> empList() {
		return empMapper.selectEmpAll();
	}

	@Override
	public EmpVO empInfo(EmpVO empVO) {
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int empInsert(EmpVO empVO) {
		int result = empMapper.insertEmpInfo(empVO);
		return result == 1 ? empVO.getEmpid() : -1; // 정상 동작시 empId를 반환 아닐땐 -1 반환
	}

	@Override
	public Map<String, Object> empUpdate(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = empMapper.updateEmpInfo(empVO.getEmpid(), empVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", empVO);
		
		return map;
	}

	@Override
	public Map<String, Object> empDelete(EmpVO empVO) {
		Map<String, Object> map = new HashMap<>();
		int result = empMapper.deleteEmp(empVO.getEmpid());
		
		if(result == 1) {
			map.put("empid", empVO.getEmpid());
		}
		return map;
	}

}
