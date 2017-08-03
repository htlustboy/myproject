package com.myproject.service.mail;

import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.myproject.dao.MailMapper;
import com.myproject.util.JMailUtil;

@Service
public class MailService {
	
	@Resource
	MailMapper mailMapper;
	
	public Map<String, String> getAccount(){
		return mailMapper.getAccount();
	}
	
	public String sendEmail(String fromAccount,String fromPassword,String toAccount,String content){
		String smtpHost = "smtp.163.com";
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", smtpHost);
		properties.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(properties);
		session.setDebug(false);
		MimeMessage message = JMailUtil.createMimeMessage(session,fromAccount,toAccount,content);
		try {
			Transport transport = session.getTransport();
			transport.connect(fromAccount, fromPassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return "发送成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败";
		}
	}
}
