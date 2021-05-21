package com.app.notebook.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
public class FeedbackMailSender implements FeedbackSender {
	private JavaMailSenderImpl mailSender;

    public FeedbackMailSender(MailConfiguration mailconfig){
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailconfig.getHost());
        mailSender.setPort(mailconfig.getPort());
        mailSender.setUsername(mailconfig.getUsername());
        mailSender.setPassword(mailconfig.getPassword());
    }
    
    @Override
    public void sendFeedback(String from, String name, String feedback){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("feedback@noteit.com");
        message.setSubject("New feedback from " + name);
        message.setText(feedback);
        message.setFrom(from);
        this.mailSender.send(message);
    }

}
