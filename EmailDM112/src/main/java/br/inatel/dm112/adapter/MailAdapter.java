package br.inatel.dm112.adapter;

import java.util.Properties;


import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class MailAdapter {

	
	public void notifyProgress(final String from, final String password, String to, int[] content, int number, int orderStatus) {

		System.out.println("Enviando email para: " + to);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {
			
			if(orderStatus == 1) {
			
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				message.setSubject("Registro de entrega, pedido: " + number);
	
				Multipart multipart = new MimeMultipart();
				BodyPart messageBodyPartText = new MimeBodyPart(); // texto
				messageBodyPartText.setText("Entrega realizada com sucesso");
				multipart.addBodyPart(messageBodyPartText);
	
				message.setContent(multipart);
				
				Transport.send(message);
				System.out.println("Sent message successfully....");
			}
			else {				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				message.setSubject("Processo de entrega, pedido: " + number);
	
				Multipart multipart = new MimeMultipart();
				BodyPart messageBodyPartText = new MimeBodyPart(); // texto
				messageBodyPartText.setText("Entrega a caminho");
				multipart.addBodyPart(messageBodyPartText);
	
				message.setContent(multipart);
				
				Transport.send(message);
				System.out.println("Sent message successfully....");
					}
			}
		 catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
