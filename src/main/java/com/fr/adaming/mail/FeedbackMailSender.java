package com.fr.adaming.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class FeedbackMailSender implements FeedbackSender {
 
	@Override
	public void sendFeedback(String from, String name, String feedback) {
	        final String username = "fahmichibani@gmail.com";
	        final String password = "*********";
 
            Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "465");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.socketFactory.port", "465");
	        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	            
	        Session session = Session.getInstance(prop,new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });
	        try {
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress());        
		       // message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("fahmichii@yahoo.fr, fahmichibani@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(from));
		        System.out.println("ggg "+from);
	            message.setSubject("send by "+name);
	            message.setText("bonjour,\n"+ feedback + "\n\n cordialement");      
	            Transport.send(message);
	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }

	}

