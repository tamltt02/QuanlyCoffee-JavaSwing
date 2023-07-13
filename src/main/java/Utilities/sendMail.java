/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ADMIN
 */
public class sendMail {

    public String getRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean GuiMa(String email, String code) {
        boolean test = false;

        String username = "tamlttph19033@fpt.edu.vn";
        String password = "vtjekpdqurdtzpfs";

        try {

            // your host email smtp server details
            Properties pr = new Properties();

            pr.put("mail.smtp.host", "smtp.gmail.com");
            pr.put("mail.smtp.port", "587");
            pr.put("mail.smtp.auth", "true");
            pr.put("mail.smtp.ssl.protocols", "TLSv1.2");
            pr.put("mail.smtp.starttls.enable", "true");

            //get session to authenticate the host email address and password
            Session session = Session.getInstance(pr,
                    new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            //set email message details
            Message message = new MimeMessage(session);
            System.setProperty("mail.mime.charset", "UTF-8");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setFrom(new InternetAddress("tamlttph19033@fpt.edu.vn"));
            message.setSubject("Xác minh Email Người dùng");
            message.setText("Vui lòng xác minh tài khoản của bạn bằng mã này: " + code);

            Transport.send(message);

            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return test;
    }
}
