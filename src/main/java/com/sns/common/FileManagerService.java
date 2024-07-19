package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



@Component
public class FileManagerService {

	// 실제 업로드가 된 이미지가 저장될 서버의 경로 
	//public static final String FILE_UPLOAD_PATH = ""; // 학원 
	public static final String FILE_UPLOAD_PATH = "C:\\나현희\\6_spring_project\\sns\\workspace\\images/"; // 집 
	
	// input: MultipartFile, userLoginId
	// output: String(이미지 경로)
	
	public String uploadFile (MultipartFile file, String loginId) {
		// 폴더 생성 
		// 예: aaaa_17348394850
		String directoryName = loginId + "_" + System.currentTimeMillis();
		
		// filePath => C:\\나현희\\6_spring_project\\sns\\workspace\\images/aaaa_17348394850
		String filePath = FILE_UPLOAD_PATH + directoryName + "/";
		
		// 폴더 생성
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			// 폴더 생성시 실패하면 경로를 null로 리턴 
			return null;
		}
		
		
		// 파일 업로드 
		try {
			byte[] bytes = file.getBytes();
			// ***** 한글명으로 된 이미지는 업로드 불가하므로 나중에 영문자로 바꾸기*******
			Path path = Paths.get(filePath+ file.getOriginalFilename());
			Files.write(path, bytes);  // 실제 파일 업로드 진행
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null; // 이미지 업로드 실패시 경로 null
		}
		
		// 파일업로드가 성공하면 이미지 url path를 리턴 
		// 주소 예언
		// http://localhost/images/test1_17348394850/sun.png
		return "/images/" + directoryName + "/" +file.getOriginalFilename();
		
	}	
		
	
}
