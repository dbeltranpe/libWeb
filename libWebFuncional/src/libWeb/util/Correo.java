package libWeb.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase que representa los m�todos para el env�o del correo 
 * @author Daniel Beltr�n Penagos
 * @author Santiago Correa Vera 
 * <br><br>
 * <center> <b> Universidad El Bosque<br>
 * Ingenier�a de Sistemas - Programaci�n 2<br>
 * Profesor Wilson Rojas Reales <br>
 * Proyecto libWeb</b> </center>
 */
public class Correo 
{
	/**
	 * Correo por el cual se van a enviar los correos 
	 */
	private final static String correo = "admonlibweb@gmail.com";
	
	/**
	 * Contrase�a del corrreo
	 */
	private final static String password = "lib.123@";
	
	/**
	 * M�todo que se conecta a internet para el env�o del correo 
	 * @param destinatario correo del destinatario para el env�o del correo
	 * @param subject Asunto del correo 
	 * @param textBody Cuerpo del correo 
	 */
	public static void enviarCorreo(String destinatario, String subject, String textBody)
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(correo, password);
			}
		});

		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(correo));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(destinatario));
			message.setSubject(subject);
			message.setText(textBody);

			Transport.send(message);
		} 
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}

	}
}