package com.poly.Controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.entities.MailInfo;
import com.poly.services.MailerService;

import jakarta.mail.MessagingException;

@Controller
@RequestMapping("mailer")

public class MailerController {
	@Autowired
	MailerService mailer;
	
	@GetMapping("/mailer")
	public String mailViewer() {
		return "mailer";
	}
	@ResponseBody
	@PostMapping("/mailer/send")
	public String send(Model model,@RequestParam String txtTo,@RequestParam String txtSubject,@RequestParam String txtContent) {
		try {
			MailInfo mail=new MailInfo();
			mail.setTo(txtTo);
			mail.setSubject(txtSubject);
			mail.setBody(txtContent);	
			mailer.send(mail);
			return "<h1>Gửi email thành công</h1>";
		} catch (MessagingException e) {
			return "<h1>Gửi email thất bại</h1>"+e.getMessage();
		}
	}


}
