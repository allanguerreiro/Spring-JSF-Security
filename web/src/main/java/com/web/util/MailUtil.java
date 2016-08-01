package com.web.util;

import com.exceptions.CannotSendMailException;
import com.model.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;

import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by allan on 27/07/16.
 */
@Slf4j
public class MailUtil {
    /**
     * Properties for activation mail.
     */
    private Configuration config;

    /**
     * Sends the user a link to reset the password.
     *
     * @param user         the user
     * @param serverString host and port of the server
     * @throws CannotSendMailException if the password reset mail could not be sent
     */
    public void sendPasswordResetMail(User user, String serverString) throws CannotSendMailException {
        try {
            Properties props = System.getProperties();

            props.put("mail.smtp.host", config.getString("mail.smtp.host"));
            Session session = Session.getDefaultInstance(props, null);

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.getString("mail.from")));
//            message.setRecipient(RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("Planningsuite password recovery");

            StringBuilder builder = new StringBuilder();
            builder.append("Dear " + user.getUsername() + ", \n\n");
            builder.append("You have requested help recovering the password for the Plato user ");
            builder.append(user.getUsername()).append(".\n\n");
            builder.append("Please use the following link to reset your Planningsuite password: \n");
            builder.append("http://" + serverString + "/idp/resetPassword.jsf?uid=" + user.getPassword());
            builder.append("\n\n--\n");
            builder.append("Your Planningsuite team");

            message.setText(builder.toString());
            message.saveChanges();

            Transport.send(message);
//            log.debug("Sent password reset mail to " + user.getEmail());

        } catch (Exception e) {
//            log.error("Error at sending password reset mail to {}", user.getEmail());
            throw new CannotSendMailException("Error at sending password reset mail to " + user.getUsername());
        }
    }
}
