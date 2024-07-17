package com.yedam.app.aaa.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AaaMapper {
	@Insert("INSERT INTO aaa VALUES (#{value})")
	public int aaaInsert(String value);
}
