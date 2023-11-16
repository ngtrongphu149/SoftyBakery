package com.poly.services;

import com.poly.models.MailInfo;

import jakarta.mail.MessagingException;

public interface MailerService {
	void send(MailInfo mail) throws MessagingException;
	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mail);
	void queue(String to, String subject, String body);
<<<<<<< HEAD

	void sendMailContact(String to, String subject, String body, String mesage);
=======
>>>>>>> 57fc63ab02aacdc4187f7d2ccc9f6e031fb48dda
}
