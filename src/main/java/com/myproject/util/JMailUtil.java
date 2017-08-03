package com.myproject.util;


import java.util.Date;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JMailUtil {

	public static MimeMessage createMimeMessage(Session session,String fromAccount,String toAccount,String content){
		//创建一封邮件
		MimeMessage message = new MimeMessage(session);
		try {
			//发件人
			message.setFrom(new InternetAddress(fromAccount, fromAccount, "UTF-8"));
			//收件人
			message.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(toAccount,toAccount,"UTF-8"));
			//设置邮件主题
			message.setSubject("测试邮件");
			//邮件正文
			message.setContent(content,"text/html;charset=UTF-8");
			//设置发送时间
			message.setSentDate(new Date());
			//保存设置
			message.saveChanges();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
