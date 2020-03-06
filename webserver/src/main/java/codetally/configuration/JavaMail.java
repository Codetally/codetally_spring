package codetally.configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by greg on 16/09/17.
 */
public class JavaMail {
    public static void SendMail(String ownername) {

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port","465");

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("service@codetally.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gemartin@gmail.com"));
            message.setSubject("Ping");
            message.setText("Hello, "+ownername+" just synched repos.");
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

