package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	// 전체 부서 정보 조회
	public List<DeptVO> deptList();
	// 부서정보 조회
//	public DeptVO deptInfo(DeptVO deptVO);
	public DeptVO deptInfo(Long deptId);
	// 부서정보 등록
	public int deptInsert(DeptVO deptVO);
	// 부서정보 수정
	public Map<String, Object> deptUpdate(DeptVO deptVO);
	// 부서정보 삭제
	public Map<String, Object> deptDelete(DeptVO deptVO);
	
}
