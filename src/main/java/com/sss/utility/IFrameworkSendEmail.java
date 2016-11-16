package com.sss.utility;

/**
 * Created by vin on 10/21/16.
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class IFrameworkSendEmail {
    public IFrameworkSendEmail(){}
    public void SendEmail(String htmlContain) throws Exception {
        // Recipient's email ID needs to be mentioned.
        String to = "hoangviet@siliconstraits.com";

        // Sender's email ID needs to be mentioned
        String from = "robot.qa.siliconstraits@gmail.com";
        final String username = "robot.qa.siliconstraits";//change accordingly
        final String password = "sssqarobot";//change accordingly

        // Assuming you are sending email through gmail
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.EnableSSL.enable", "true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Send the actual HTML message, as big as you like
            message.setContent(
                    htmlContain,
                    "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void SendEmailWithHTML(String htmlFileLocation) throws Exception {
        IFrameworkFileAndDirectory iDirectory = new IFrameworkFileAndDirectory();
        String htmlFolder = iDirectory.getLatestDirectory(htmlFileLocation);
        String htmlContain = iDirectory.readFile(htmlFolder + "emailable-report.html", StandardCharsets.UTF_8);
        // Recipient's email ID needs to be mentioned.
        String to = "hoangviet@siliconstraits.com";

        // Sender's email ID needs to be mentioned
        String from = "robot.qa.siliconstraits@gmail.com";
        final String username = "robot.qa.siliconstraits";//change accordingly
        final String password = "sssqarobot";//change accordingly

        // Assuming you are sending email through gmail
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.EnableSSL.enable", "true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Testing Subject");

            // Send the actual HTML message, as big as you like
            message.setContent(
                    htmlContain,
                    "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
