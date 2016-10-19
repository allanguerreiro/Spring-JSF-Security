package com.web.bbean;

import com.model.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * Created by allan on 22/07/16.
 */
@Named("userBBean")
@Scope("session")
@Slf4j
public class UserBBean implements Serializable {
    @Getter
    @Setter
    private User user;

    public UserBBean() {
        user = new User();
        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {
            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {
                user.setUsername(String.valueOf(authentication.getPrincipal()));
            }
        }
    }

    public User getUsuario() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        user = (User) session.getAttribute("usuarioLogado");

        return user;
    }
}
