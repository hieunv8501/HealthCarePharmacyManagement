package Helpers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;
import org.mindrot.jbcrypt.BCrypt;
import Controllers.TaikhoanController;
import Models.Taikhoan;

public class Email {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String specials = "~%!@#$^&()[]{}=+-_|/\\*;,?<>:."; //speacials, không chứa ký tự " dễ nhầm với '
    private static final String ALL = alpha + alphaUpperCase + digits + specials; //all   
    private static Random generator = new Random();
    
    public static String randomPassword(int numberOfCharactor) {
        List<String> result = new ArrayList<>();
        Consumer<String> appendChar = s -> {
            int number = randomCharacter(0, s.length() - 1);
            result.add("" + s.charAt(number));
        };
        appendChar.accept(digits);
        appendChar.accept(alpha);
        appendChar.accept(alphaUpperCase);
        appendChar.accept(specials);
        while (result.size() < numberOfCharactor) {
            appendChar.accept(ALL);
        }
        Collections.shuffle(result, generator);
        return String.join("", result);
    }
 
    public static int randomCharacter(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    
    public void saveHashPwd(Taikhoan tk, String pwdToHash){    
        if (pwdToHash != null && !pwdToHash.isEmpty()) {
          int round = 10;       
          tk.resetMatKhau(tk, BCrypt.hashpw(pwdToHash, BCrypt.gensalt(round)));    
        }
    }
    
    public void sendEmail (Taikhoan tk, String receiverEmailAddress) throws MessagingException, UnsupportedEncodingException{
        //Địa chỉ email người gửi (system)
        final String senderEmail = "bolaxanhcoffee@gmail.com";
        //Mật khẩu email người gửi (system)
        final String senderPwd = "bolaxanh148.";
        //Địa chỉ emai; người nhận
        final String receiverEmail = receiverEmailAddress;
        final String subject = "Password Reset Confirm";    
       
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPwd);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
        msg.setFrom(new InternetAddress(senderEmail, "HealthCare Pharmacy Management System"));
        msg.setReplyTo(InternetAddress.parse(senderEmail, false));        
        msg.setReplyTo(InternetAddress.parse(receiverEmail, false));
        msg.setSubject(subject, "UTF-8");      
        //Mail body
        String randomNewPwd = Email.randomPassword(6);
        saveHashPwd(tk, randomNewPwd);
        final String body = "Your password to the HealthCare Pharmacy Management System has been reset. <br/>The new one is: <b>" + randomNewPwd + "</b>";     
        msg.setContent(body, "text/html");
        msg.setSentDate(new Date());
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail, false));
        Transport.send(msg);     
    }
}