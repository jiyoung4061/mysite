package com.markany.mysite.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static final String SAVE_PATH="/mysite-uploads";
	private static final String URL_BASE="/images";

	public String restore(MultipartFile multipartFile) {
		String url = "";

		try {
			String originFilename = multipartFile.getOriginalFilename();
			String extName = originFilename.substring(originFilename.lastIndexOf('.')+1); // ex. profile.20210107.jpg이면 마지막 .을 찾는다!
			String saveFilename = genSaveFilename(extName);
			Long fileSize = multipartFile.getSize();

			System.out.println("--->" + originFilename);
			System.out.println("--->" + saveFilename);
			System.out.println("--->" + fileSize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" +saveFilename);
			os.write(fileData);
			os.close();
			
			url=URL_BASE + "/" + saveFilename;
			
		} catch (IOException e) {
			throw new RuntimeException("file upload error:"+ e);
		}

		return url;
	}

	private String genSaveFilename(String extName) {
		String filename = "";
		/*
		 * date Class는 없어질 경우가 많음 => Calendar Class사용하기!
		 * new Date
		 * Calender는 new X
		 */
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR); // year가 나옴
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		
		filename += ("."+extName);
		return filename;
	}

}
