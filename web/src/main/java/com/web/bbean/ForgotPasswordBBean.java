package com.web.bbean;

import com.exceptions.CannotSendMailException;
import com.exceptions.UserNotFoundException;
import com.model.domain.User;
import com.service.IUserService;
import com.web.util.MailUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by allan on 27/07/16.
 */
@Named("forgotPasswordBBean")
@Scope("session")
@Slf4j
public class ForgotPasswordBBean {
    @Getter
    @Setter
    private String userIdentifier;

    @Inject
    private IUserService userService;

    /**FK_USERNAME
     * Resets the password of the user identified by email.
     */
    public void resetPassword() throws UserNotFoundException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String serverString = request.getServerName() + ":" + request.getServerPort();

        try {
            User user = new User();
            user = userService.getUserByUserName(userIdentifier);
            userService.initiateResetPassword(user);

            new MailUtil().sendPasswordResetMail(user, serverString);
            FacesContext.getCurrentInstance().
                    addMessage(null, new
                            FacesMessage(FacesMessage.SEVERITY_INFO,
                            "A mail with password recovery information has been sent to the email " +
                                    "address provided when you created the account.", "Message: "));
        } catch (CannotSendMailException csme) {
            FacesContext.getCurrentInstance().
                    addMessage(null, new
                            FacesMessage(FacesMessage.SEVERITY_ERROR, "Error sending the password reset mail.", csme.getMessage()));
        }
    }
}
