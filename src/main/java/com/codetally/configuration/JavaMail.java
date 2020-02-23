package com.codetally.configuration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by greg on 16/09/17.
 */
public class JavaMail {
    public static void SendMail(String ownername) {

        //Get the session object
        Properties properties = System.getProperties();


        properties.setProperty("mail.smtp.host", "email-smtp.us-east-1.amazonaws.com");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port","465");

        /*properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");*/

        //Session session = Session.getDefaultInstance(properties);
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("AKIAI7SK7PVFHP4QBTZA", "AoJbldQh4mUDmTt6wda4JhaPkdWNYngT4MgIQodL41TA");
                    }
                });

        //compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("service@codetally.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gemartin@gmail.com"));
            message.setSubject("Ping");
            message.setText("Hello, "+ownername+" just synched repos.");
            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

