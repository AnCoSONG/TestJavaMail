package mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestMain {
	
	//发件人 邮箱 和 密码
	// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
	//     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	/**发件人邮箱账户*/
	public static String myEmailAccount = "1197252996@qq.com";
	/**发件人密码*/
	public static String myEmailPassword = "Sjy525923+";
	/**发件人邮箱SMTP地址*/
	public static String myEmailSMTPHost = "smtp.qq.com";
	/**收件人邮箱*/
	public static String receiveMailAccount = "songjinyu23@outlook.com";
	
	public TestMain() {
		//1. 创建参数配置 用于连接邮件服务器配置参数
		//参数配置
		Properties props = new Properties();
		//使用的协议(SMTP为JavaMail规范要求)
		props.setProperty("mail.transport.protocol", "smtp");
		//发件人的邮箱的SMTP服务器地址
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		//需要请求认证
		props.setProperty("mail.smtp.auth", "true");
		/*对于一些需要使用SSL安全认证的SMTP服务器 
		 * SMTP端口(非SSL连接的SMTP端口一般为25 开启SSL连接需要改为对应邮箱
		 * 的SMTP(SSL)端口，一般为为465或587)
		 */
		final String smtpPort = "465";
		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);
		
		//2. 根据配置创建会话对象，用于和邮件服务器交互
		Session session = Session.getInstance(props);
		session.setDebug(true);
		
		//3. 创建一封邮件 
		MimeMessage message = createMimeMessage(session,myEmailAccount,receiveMailAccount);
		
		//4. 根据Session 获取邮件传输对象
		try {
			Transport transport = session.getTransport();
			//5. 使用 邮箱账号 和 密码 连接邮箱服务器 这里认证的邮箱必须
			//与message中的发件箱一致，否则报错
			transport.connect(myEmailAccount, myEmailPassword);
			//发送邮件 发到所有的收件地址 获取到的是创建邮箱对象时添加的所有收件人 抄送 密送
			transport.sendMessage(message,message.getAllRecipients());
			//关闭连接
			transport.close();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private MimeMessage createMimeMessage(Session session, String myEmailAccount2, String receiveMailAccount2) {
		// TODO Auto-generated method stub
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		int codeLength = 4;
		for(int i = 0; i < codeLength; i ++) {
			sb.append(random.nextInt(10));
		}
		String code = sb.toString();
		new SaveCode(code);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myEmailAccount2, "AnCo Sun", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount2, "SJY OUTLOOK", "UTF-8"));
			message.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress("songjinyu23@gmail.com", "SJY GMAIL", "UTF-8"));
			message.setSubject("Test", "UTF-8");
			message.setContent("<p>这是一封测试邮件</p><h1>这是验证码:"+code+"</h1>", "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	
	public static void main(String[] args) {
		new TestMain();
	}

}
