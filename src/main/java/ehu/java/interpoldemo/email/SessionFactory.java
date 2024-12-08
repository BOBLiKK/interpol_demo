package ehu.java.interpoldemo.email;

import java.util.Properties;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

public class SessionFactory {
    public static Session createSession(Properties configProperties) { String userName =
            configProperties.getProperty("mail.user.name");
        String userPassword = configProperties.getProperty("mail.user.password");
        return Session.getDefaultInstance(configProperties,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}