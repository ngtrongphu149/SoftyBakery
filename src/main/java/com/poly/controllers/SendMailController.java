package com.poly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
import com.poly.models.MailInfo;
import com.poly.services.AccountService;
import com.poly.services.MailerService;


@Controller
public class SendMailController {
    @Autowired
	MailerService mailerService;
	@Autowired ProductDAO pDAO;
	@Autowired AccountDAO aDAO;
	@Autowired CategoryDAO cDAO;
    @Autowired AccountService accountService;
 
    @PostMapping("/contactMail")
	public String contactSendMail(Model model,@RequestParam String email,@RequestParam String name) {
	MailInfo mail = new MailInfo();
	mail.setTo(email);
	mail.setSubject("Cảm ơn bạn đã liên hệ với Softy Bakery");
	StringBuilder bodyBuilder = new StringBuilder();
bodyBuilder.append("<h4>Xin chào " + name + ", </h4>");
bodyBuilder.append("<p> >Chúng tôi xin chân thành cảm ơn bạn đã liên hệ với Softy Bakery . Chúng tôi rất vui mừng được hỗ trợ bạn và trả lời mọi câu hỏi của bạn</p>");
bodyBuilder.append("<p>Chúng tôi đã nhận được thông điệp của bạn và sẽ xem xét nhanh chóng. Đội ngũ chăm sóc khách hàng của chúng tôi sẽ cố gắng trả lời bạn trong thời gian sớm nhất có thể.</p>");
bodyBuilder.append("<p>Nếu bạn có bất kỳ yêu cầu hoặc thắc mắc cụ thể, đừng ngần ngại liên hệ trực tiếp với chúng tôi qua địa chỉ email [bakerysofty@gmail.com] hoặc số điện thoại [(+84) 869945854]</p>");
bodyBuilder.append("Chúng tôi mong muốn được phục vụ bạn và hy vọng rằng chúng tôi có thể đáp ứng đúng mong đợi của bạn.");
		mail.setBody(bodyBuilder.toString());
        mailerService.queue(mail);	
		
System.out.println(email);
		return "redirect:/home";
	}
}