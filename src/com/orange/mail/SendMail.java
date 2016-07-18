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
	
	private String host = null;//stmp��������ַ
	private String username = null;//�û���
	private String password = null;//����
	private String sendAddress = null;//�����˵�ַ
	private String receiveAddress = null;//�ռ��˵�ַ
	private String title = null;//�ʼ�����
	
	public boolean send(String content){
		Properties p = new Properties();
		p.put("mail.smtp.host", host);//���÷����ʼ��ķ�����������
		p.put("mail.smtp.auth", "true");//��Ҫ����У����Ȩ
		Session session = Session.getDefaultInstance(p);//����session
		//session.setDebug(true);//���͵�����Ϣ������Console�ڲ鿴
		MimeMessage msg = new MimeMessage(session);
		try {
			msg.setFrom(sendAddress);//���÷����˵�ַ
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveAddress));//�����ռ���
			msg.setSubject(title);//�����ʼ�����
			
			Multipart multipart = new MimeMultipart();//��Multipart����������ʼ��������֣������ı��͸���
			//�����ʼ����ı�����
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);
			multipart.addBodyPart(contentPart);
		   // ��Ӹ���
		   //BodyPart messageBodyPart = new MimeBodyPart();
		   //DataSource source = new FileDataSource(affix);
		   // ��Ӹ���������
		   //messageBodyPart.setDataHandler(new DataHandler(source));
		   // ��Ӹ����ı���
		   // �������Ҫ��ͨ�������Base64�����ת�����Ա�֤������ĸ����������ڷ���ʱ����������
		   //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
		   //messageBodyPart.setFileName("=?GBK?B?"+ enc.encode(affixName.getBytes()) + "?=");
		   //multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);//����ʼ�����
			msg.saveChanges();//�����ʼ�
			//�����ʼ�
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);//�����ӷ�����
			transport.sendMessage(msg, msg.getAllRecipients());//���ʼ����ͳ�ȥ
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
