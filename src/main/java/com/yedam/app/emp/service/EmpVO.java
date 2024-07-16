package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpVO {
	private Integer empid;
	private String empname;
	private int mgr;
	private int sal;
	private Integer deptid;
	@DateTimeFormat(pattern = "yyyy-MM-dd") // 파라미터 자동변환할때 사용
	private Date hiredate;
}
