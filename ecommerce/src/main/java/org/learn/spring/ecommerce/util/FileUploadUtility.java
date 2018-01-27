package org.learn.spring.ecommerce.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	private static final String ABS_PATH = "D:\\project\\Spring\\Spring\\ecommerce\\src\\main\\webapp\\resources\\images\\";
	private static String REAL_PATH = "";
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		REAL_PATH = request.getSession().getServletContext().getRealPath("/resources/images/");
		//if directory does not exists create one
		
		if(!new File(ABS_PATH).exists())
			new File(ABS_PATH).mkdirs();
		if(!new File(REAL_PATH).exists())
			new File(REAL_PATH).mkdirs();
		
		try {
			//upload to server
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			//upload to project dir
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
