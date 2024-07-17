package com.yedam.app.aaa.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
public class UploadController {
	@Value("${file.upload.path}") // 환경변수로 들어온 경로값을 읽어서 넣어줌
	private String uploadPath;
	
	// 운영서버는 리눅스인데 리눅스는 D드라이브 개념이 없기 때문에 이 경로는 고정값이 아님 
//	private String uploadPath = "D:/upload";
	
	@GetMapping("formUpload")
	public void formUploadPage() {
		
	}
	// form 태그에 multiple이 있으면 반드시 MultipartFile이 배열로 선언되어야한다. 
	// 만약 여러건 처리를 하지 않는다면 배열이 아니어도 상관없다.
	// enctype="multipart/form-data" => @RequestPart, @PostMapping
	// MultipartResolver => Content-type : Multipart/form-data
	@PostMapping("uploadForm") // 중요!! 변수명(images)은 form의 name 속성을 따라가야한다. 
	public String formUploadFileString(@RequestPart MultipartFile[] images) {
		for (MultipartFile image : images) {
			log.info("imageContentType={}", image.getContentType());
			log.info("imageOriginalFilename={}", image.getOriginalFilename());
			log.info("imageSize={}", String.valueOf(image.getSize()));
			// 1) 원래 파일이름
			String fileName = image.getOriginalFilename();
			// 2) 실제 저장할 경로를 생성 : 서버의 업로드 경로 + 파일이름 합치
			String saveName = uploadPath + File.separator + fileName;
			log.info("saveName={}", saveName);
			
				// Path => 실제 pc의 경로로 변환하는 작업
			Path savePath = Paths.get(saveName);
			// 3) 파일 작성(파일 업로드) D:/upload(실습이니까 간단하게)
			try {
				// MultipartFile이 제공하는 메서드 transferTo() 
				image.transferTo(savePath);
			} catch (IOException e) {
				log.error("err={}", e);
				e.printStackTrace();
			}
		}
		// 나중에 리다이렉트로 바꿔줘야함 PRG
		return "formUpload";
	}
}
