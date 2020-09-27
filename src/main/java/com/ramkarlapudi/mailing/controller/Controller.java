package com.ramkarlapudi.mailing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ramkarlapudi.mailing.model.MailUtil;
import com.ramkarlapudi.mailing.service.MailingServiceImpl;

@Component
public class Controller implements CommandLineRunner  {

	@Autowired
	private MailingServiceImpl mailingServiceImpl ;
	@Autowired
	private MailUtil mailUtils;
	@Override
	public void run(String... args) throws Exception {
		mailUtils.setCc(new String[] {"ramkarlapudi@hotmail.com"});
		mailUtils.setSubject("Test Mail from JavaMailSender SpringBoot");
		mailUtils.setText("Hello There");
		mailUtils.setTo("ramkarlapudi1995@gmail.com");
		
	//Boolean flag =	mailingServiceImpl.sendMail();
	
	if(mailingServiceImpl.sendMail()) {
		System.out.println("mail sent" );
	}else{
		System.out.println("mail not  sent" );
	}
		
	}
	
	
}
