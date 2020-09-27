package com.ramkarlapudi.mailing.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ramkarlapudi.mailing.model.MailUtil;

@Component
public class MailingServiceImpl implements MailingService {

	@Autowired
	private MailUtil mailUtil;
	
	@Autowired
	private JavaMailSender  javaMailSender ;
	
	@Override
	public Boolean sendMail() {
		 boolean flag = false;
		MimeMessage message = javaMailSender.createMimeMessage();
			
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, mailUtil.getFile()!=null?true:false);
			helper.setTo(mailUtil.getTo());
			helper.setSubject(mailUtil.getSubject());
			helper.setText(mailUtil.getText());
			
			if(mailUtil.getCc()!= null &&  mailUtil.getCc().length > 0) {
				helper.setCc(mailUtil.getCc());
			}
			if( mailUtil.getBcc() != null &&   mailUtil.getBcc().length > 0 ) {
				helper.setBcc(mailUtil.getBcc());
			}
			
			if( mailUtil.getFile() != null ) {
				helper.addAttachment(mailUtil.getFile().getFilename(), mailUtil.getFile());
			}
			
			javaMailSender.send(message);
			flag = true ;
		} catch (MessagingException e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
		
		
	}

}
