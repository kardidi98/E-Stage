package com.stage.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.stage.entities.Utilisateur;

@Service
public class MailNotificationService {

	

	public void sendNotification(Utilisateur receiver, String msg) throws MailException{


		final String username = "InternshipTeam2020@gmail.com";
		final String password = "InternshipManagers2020";
		final String host= "smtp.gmail.com";
		final int port=587;
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("InternshipTeam2020@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receiver.getEmail()));
			message.setSubject("Internship Team");
			message.setContent(msg,"text/html");

			Transport transport = session.getTransport("smtp");
			transport.connect (host, port,username,password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();  


		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}
