package mail;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestJavaMail {

	public TestJavaMail() {
		
		//1.创建一封邮件
		/**用于连接邮件服务器的参数配置（发送邮件时用到）*/
		Properties prop = new Properties();
		/**根据参数配置创建会话对象(为发送邮件准备)*/
		Session session = Session.getDefaultInstance(prop);
		/**创建邮件对象*/
		MimeMessage message = new MimeMessage(session);
		
		try {
			//2.设置发件人from InternetAddress包含最多三个参数：发件人邮箱地址 发件人显示昵称 发件人昵称字符编码
			message.setFrom(new InternetAddress("1197252996@qq.com", "AnCo", "UTF-8"));
			//3.设置收件人 可选则setRecipients设置多个收件人信息
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("songjinyu23@outlook.com", "sjy outlook mail", "UTF-8"));
			//(可选)添加收件人
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("songjinyu23@gmail.com", "sjy gmail", "UTF-8"));
			/*(可选)设置抄送或密送
			message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("ee@receive.com", "USER_EE", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.BCC, new InternetAddress("ff@receive.com", "USER_FF", "UTF-8"));
			*/
			//4.设置邮件标题
			message.setSubject("JAVA MAIL TEST", "UTF-8");
			//5.设置邮件正文 可以使用html标签 
			message.setContent("这是一封测试邮件", "text/html;charset=UTF-8");
			//6.设置显示的发件时间
			message.setSentDate(new Date());
			//7.保存前面的设置
			message.saveChanges();
			//8.将该邮件保存在本地
			OutputStream out = new FileOutputStream("TestMail.eml");
			message.writeTo(out);
			out.flush();
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new TestJavaMail();
	}

}
