package ehu.java.interpoldemo.email;

import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailSender {

    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public EmailSender(String sendToEmail, String mailSubject, String mailText, Properties props) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = props;
    }

    public void send() {
        try {
            initMessage();
            Transport.send(message);
        } catch (AddressException e) {
            System.err.println("Invalid address: " + sendToEmail + " " + e);
        } catch (MessagingException e) {
            System.err.println("Error generating or sending message: " + e);
        }
    }

    private void initMessage() throws MessagingException {
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(mailSubject);
        message.setContent(mailText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
    }
}