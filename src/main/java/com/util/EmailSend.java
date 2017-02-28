package com.util;

import com.config.Constants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Map;

public class EmailSend {

	private static Logger logger = LogManager.getLogger(EmailSend.class);

	public static void sendEmail(String title, String text, List<String> list) {
		try {
			HtmlEmail email = new HtmlEmail();
			Multipart multipart = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			email.setCharset("UTF-8");
			email.setHostName(Constants.hostName);
			for (int i = 0; i < list.size(); i++) {
				email.addTo(list.get(i));
			}
			email.setFrom(Constants.senderEmail, Constants.senderName);
			email.setAuthentication(Constants.sender, Constants.pwd);
			email.setSubject(title);
			email.setMsg(text);
			email.send();
			System.out.println("success!");
		} catch (EmailException e) {
			System.out.println("发送失败");
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void sendEmail(String title, String text, String humer) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName(Constants.hostName);
			email.addTo(humer);
			email.setFrom(Constants.senderEmail, Constants.senderName);
			email.setAuthentication(Constants.sender, Constants.pwd);
			email.setSubject(title);
			email.setMsg(text);
			email.send();
			System.out.println("success!");
		} catch (EmailException e) {
			System.out.println("发送失败");
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static boolean sendHtmlEmail(String title, String text, String humer) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName(Constants.hostName);
			email.addTo(humer);
			email.setFrom(Constants.senderEmail, Constants.senderName);
			email.setAuthentication(Constants.sender, Constants.pwd);
			email.setSubject(title);
			email.setHtmlMsg(text);
			email.send();
			System.out.println("success!");
			return true;
		} catch (EmailException e) {
			System.out.println("发送失败");
			logger.error("", e);
			e.printStackTrace();
			return false;
		}
	}

	public static void sendHtmlEmail(String title, String humer, Map csMap) {
		String text = CommonUtil.sendTemplate(title, csMap);
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName(Constants.hostName);
			email.addTo(humer);
			email.setFrom(Constants.senderEmail, Constants.senderName);
			email.setAuthentication(Constants.sender, Constants.pwd);
			email.setSubject(title);
			email.setHtmlMsg(text);
			email.send();
			System.out.println("success!");
		} catch (EmailException e) {
			System.out.println("发送失败");
			logger.error("", e);
			e.printStackTrace();
		}
	}

	public static void sendHtmlEmail(String title, String text, List<String> list) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("UTF-8");
			email.setHostName(Constants.hostName);
			for (int i = 0; i < list.size(); i++) {
				email.addTo(list.get(i));
			}
			email.setFrom(Constants.senderEmail, Constants.senderName);
			email.setAuthentication(Constants.sender, Constants.pwd);
			email.setSubject(title);
			email.setHtmlMsg(text);
			email.send();
			System.out.println("success!");
		} catch (EmailException e) {
			System.out.println("发送失败");
			logger.error("", e);
			e.printStackTrace();
		}
	}

}
