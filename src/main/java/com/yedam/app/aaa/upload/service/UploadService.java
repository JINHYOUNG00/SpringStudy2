package com.yedam.app.aaa.upload.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public String imageUpload(MultipartFile uploadFile);
}
