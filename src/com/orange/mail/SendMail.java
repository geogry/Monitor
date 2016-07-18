package com.orange.mail;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	private String host = null;//stmp服务器地址
	private String username = null;//用户名
	private String password = null;//密码
	private String sendAddress = null;//发件人地址
	private String receiveAddress = null;//收件人地址
	private String title = null;//邮件标题
	
	public boolean send(String content){
		Properties p = new Properties();
		p.put("mail.smtp.host", host);//设置发送邮件的服务器的属性
		p.put("mail.smtp.auth", "true");//需要经过校验授权
		Session session = Session.getDefaultInstance(p);//生成session
		//session.setDebug(true);//发送调试信息，可在Console口查看
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(sendAddress);//设置发送人地址
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));//设置收件人
			msg.setSubject(title);//设置邮件标题
			
			Multipart multipart = new MimeMultipart();//向Multipart对象中添加邮件各个部分，包括文本和附件
			//设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);
			multipart.addBodyPart(contentPart);
		   // 添加附件
		   //BodyPart messageBodyPart = new MimeBodyPart();
		   //DataSource source = new FileDataSource(affix);
		   // 添加附件的内容
		   //messageBodyPart.setDataHandler(new DataHandler(source));
		   // 添加附件的标题
		   // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
		   //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		   //messageBodyPart.setFileName("=?GBK?B?"+ enc.encode(affixName.getBytes()) + "?=");
		   //multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);//添加邮件内容
			msg.saveChanges();//保存邮件
			//发送邮件
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);//；连接服务器
			transport.sendMessage(msg, msg.getAllRecipients());//将邮件发送出去
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSendAddress(String sendAddress) {
		this.sendAddress = sendAddress;
	}
	
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
