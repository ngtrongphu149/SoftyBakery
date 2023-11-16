package com.poly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
import org.springframework.http.ResponseEntity;
=======
>>>>>>> 57fc63ab02aacdc4187f7d2ccc9f6e031fb48dda
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.poly.dao.AccountDAO;
import com.poly.dao.CategoryDAO;
import com.poly.dao.ProductDAO;
<<<<<<< HEAD
import com.poly.models.Account;
=======
>>>>>>> 57fc63ab02aacdc4187f7d2ccc9f6e031fb48dda
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
<<<<<<< HEAD
bodyBuilder.append("<p>Chúng tôi xin chân thành cảm ơn bạn đã liên hệ với Softy Bakery . Chúng tôi rất vui mừng được hỗ trợ bạn và trả lời mọi câu hỏi của bạn</p>");
bodyBuilder.append("<p>Chúng tôi đã nhận được thông điệp của bạn và sẽ xem xét nhanh chóng. Đội ngũ chăm sóc khách hàng của chúng tôi sẽ cố gắng trả lời bạn trong thời gian sớm nhất có thể.</p>");
bodyBuilder.append("<p>Nếu bạn có bất kỳ yêu cầu hoặc thắc mắc cụ thể, đừng ngần ngại liên hệ trực tiếp với chúng tôi qua địa chỉ email bakerysofty@gmail.com hoặc số điện thoại (+84)869945854</p>");
=======
bodyBuilder.append("<p> >Chúng tôi xin chân thành cảm ơn bạn đã liên hệ với Softy Bakery . Chúng tôi rất vui mừng được hỗ trợ bạn và trả lời mọi câu hỏi của bạn</p>");
bodyBuilder.append("<p>Chúng tôi đã nhận được thông điệp của bạn và sẽ xem xét nhanh chóng. Đội ngũ chăm sóc khách hàng của chúng tôi sẽ cố gắng trả lời bạn trong thời gian sớm nhất có thể.</p>");
bodyBuilder.append("<p>Nếu bạn có bất kỳ yêu cầu hoặc thắc mắc cụ thể, đừng ngần ngại liên hệ trực tiếp với chúng tôi qua địa chỉ email [bakerysofty@gmail.com] hoặc số điện thoại [(+84) 869945854]</p>");
>>>>>>> 57fc63ab02aacdc4187f7d2ccc9f6e031fb48dda
bodyBuilder.append("Chúng tôi mong muốn được phục vụ bạn và hy vọng rằng chúng tôi có thể đáp ứng đúng mong đợi của bạn.");
		mail.setBody(bodyBuilder.toString());
        mailerService.queue(mail);	
		
System.out.println(email);
		return "redirect:/home";
	}
<<<<<<< HEAD
	// forgotPassWord
@PostMapping("/forgotPassWord")
public ResponseEntity<String> sendPassword(@RequestParam String username) {
    Account account = aDAO.getByUsername(username);

    if (account != null) {
        MailInfo mail = new MailInfo();
        mail.setTo(account.getEmail());
        mail.setSubject("Your Password Recovery");
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("<p>Mật khẩu của bạn là:</p>");
        bodyBuilder.append("<h4>").append(account.getPassword()).append("</h4>");
        bodyBuilder.append("<p>Để bảo mật bạn nên xóa email này.</p>");
        mail.setBody(bodyBuilder.toString());
        mailerService.queue(mail);
		System.out.println(account.getEmail());
System.out.println(account.getPassword());
        return ResponseEntity.ok("Password recovery initiated. Check your email for further instructions.");
    } else {
        return ResponseEntity.status(400).body("Lỗi");
    }
}
}

=======
}
>>>>>>> 57fc63ab02aacdc4187f7d2ccc9f6e031fb48dda
