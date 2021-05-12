package MorozIA.otherMorozIA;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class ForgotPasswordClass {
    private static String USER_NAME = "driveave.cars@mail.ru";
    private static String PASSWORD = "polmjilo4";

    public static void mail(String mail,String password,String name) {
        String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { mail };
        String subject = "Восстановление доступа к аккаунту";
        String nbody = "jtry";
        String body =
                "Ваш пароль для восстановления доступа к аккаунту DriveAve: "+password;

        sendFromGMail(from, pass, to, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.mail.ru";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");//587
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {

            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

