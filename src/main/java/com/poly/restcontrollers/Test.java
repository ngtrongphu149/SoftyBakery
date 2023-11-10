package com.poly.restcontrollers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.*;
import com.poly.dto.RoleEnum;
import com.poly.models.*;
import com.poly.services.*;
import com.poly.utils.*;

@RestController
@CrossOrigin(origins = "*")
public class Test {
	@Autowired
	CategoryDAO cDAO;
	@Autowired
	AccountDAO aDAO;
	@Autowired
	ProductDAO pDAO;
	@Autowired
	ProductImageDAO piDAO;
	@Autowired
	OrderDAO oDAO;
	@Autowired
	OrderItemDAO oiDAO;
	@Autowired
	ReviewDAO rDAO;
	@Autowired
	UploadMediaImgurUtil uploadUtil;
	@Autowired
	AccountService accountService;
	@Autowired
	JsonReaderUtil jsonReaderUtil;
	ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/okay")
	public String test(Model model) {
		setPassword();
		return "meo meo meo meo";
	}

	public Account getAccountAuth() {
		return accountService.getAccountAuth();
	}

	public void testAccountInOrder() {
		Order o = oDAO.findById(10020).get();
		if (o.getAccount() == null) {
			System.out.println("ngu");
		} else {
			System.out.println(o.toString());
		}
	}

	public String saveAccountPhoto(String photo) {
		Account a = getAccountAuth();
		a.setPhoto(photo);
		aDAO.save(a);
		System.out.println(a.getPhoto());
		return a.getPhoto();
	}

	public void setPassword() {
		for (Account a : aDAO.findAll()) {
			a.setBanned(false);
			a.setReasonBanned("");

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			// Sử dụng formatter để parse LocalDate
			LocalDate localDate = LocalDate.parse("2000-01-01", formatter);

			// Chuyển đổi LocalDate thành Date
			Date dateOfBirth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

			a.setBirthDay(dateOfBirth);
			aDAO.save(a);
			System.out.println("nghia ngu");
		}
	}
}
