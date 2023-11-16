package com.poly.restcontrollers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.dao.*;
import com.poly.dto.DiscountType;
import com.poly.dto.RoleEnum;
import com.poly.models.*;
import com.poly.services.*;
import com.poly.utils.*;

@RestController
@CrossOrigin(origins = "*")
// @RequestMapping("/rest")
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
	@Autowired
	CouponDAO couponDAO;
	ObjectMapper objectMapper = new ObjectMapper();

	@GetMapping("/test")
	public Order test(Model model) throws JsonProcessingException {
		return oDAO.findById(9).get();
	}

	public static Date generateRandomDate() {
		Random random = new Random();
		int year = random.nextInt(11) + 1995;
		int month = random.nextInt(12) + 1;
		int day = random.nextInt(30) + 1;
		Calendar calendar = new GregorianCalendar(year, month - 1, day);
		return calendar.getTime();
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

	// public void setPassword() {
	// for (Account a : aDAO.findAll()) {
	// a.setBanned(false);
	// a.setReasonBanned("");

	// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// // Sử dụng formatter để parse LocalDate
	// LocalDate localDate = LocalDate.parse("2000-01-01", formatter);

	// // Chuyển đổi LocalDate thành Date
	// Date dateOfBirth =
	// Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	// a.setBirthDay(dateOfBirth);
	// aDAO.save(a);
	// System.out.println("nghia ngu");
	// }
	// }

	public void coupon() {

		Coupon coupon = new Coupon();
		// = couponDAO.findAll().get(0);
		// System.out.println(coupon.toString());
		coupon.setCouponId(1);
		coupon.setCouponCode("MENDAY1111");
		coupon.setDiscountType(DiscountType.Percentage);
		coupon.setDiscountValue(20);
		coupon.setMaxUsage(1);
		coupon.setStartDate(LocalDateTime.of(2023, 11, 11, 23, 55));
		coupon.setEndDate(LocalDateTime.of(2023, 11, 20, 23, 55));
		coupon.setMinPurchaseAmount(0.0);
		couponDAO.save(coupon);
		couponDAO.findAll().stream().forEach((c) -> System.out.println(c.toString()));
	}
}
