package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

	private final DeptMapper deptMapper;
	
	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DeptVO deptInfo(Long deptId) {
		return deptMapper.selectDeptInfo(deptId);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		
		return result == 1 ? deptVO.getDepartmentId() : -1; // 정상 등록시 department_id 반환 아닐시 -1;
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccessed = false;
		
		int result = deptMapper.updateDeptInfo(deptVO.getDepartmentId(), deptVO);
		
		if(result == 1) {
			isSuccessed = true;
		}
		map.put("result", isSuccessed);
		map.put("target", deptVO);
		
		return map;
	}

	@Override
	public Map<String, Object> deptDelete(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		int result = deptMapper.deleteDept(deptVO.getDepartmentId());
		
		if(result == 1) {
			map.put("deptid", deptVO.getDepartmentId());
		}
		return map;
	}

}
