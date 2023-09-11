package com.poly.services;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {

	@Autowired
	HttpServletRequest request;
	@Autowired
	ServletContext context;
	@Autowired
	private ResourceLoader resourceLoader;
	public String getString(String name, String defaultValue) {
		String result = request.getParameter(name);
		return result != null ? result : defaultValue;
	}

	public int getInt(String name, int defaultValue) {
		String result = request.getParameter(name);
		return result != null ? Integer.parseInt(result) : defaultValue;
	}


	public double getDouble(String name, double defaultValue) {
		String result = request.getParameter(name);
		return result != null ? Double.parseDouble(result) : defaultValue;
	}


	public boolean getBoolean(String name, boolean defaultValue) {
		String result = request.getParameter(name);
		return result != null ? Boolean.parseBoolean(result) : defaultValue;
	}

	/**
	 * Đọc giá trị thời gian của tham số
	 *
	 * @param name    tên tham số
	 * @param pattern là định dạng thời gian
	 * @return giá trị tham số hoặc null nếu không tồn tại
	 * @throws RuntimeException lỗi sai định dạng
	 */
	public Date getDate(String name, String pattern) {
		try {
			SimpleDateFormat formater = new SimpleDateFormat(pattern);
			return formater.parse(request.getParameter(name));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Lưu file upload vào thư mục
	 *
	 * @param file chứa file upload từ client
	 * @param path đường dẫn tính từ webroot
	 * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
	 * @throws Exception
	 * @throws RuntimeException lỗi lưu file
	 */
	public File save(MultipartFile file, String path) throws Exception {
		Resource resource = resourceLoader.getResource("classpath:/static/images/");
		File dir = resource.getFile();
		if (!dir.exists())
			dir.mkdirs();

		try {
			File saveFile = new File(dir, file.getOriginalFilename());
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new Exception();
		}
	}
}
