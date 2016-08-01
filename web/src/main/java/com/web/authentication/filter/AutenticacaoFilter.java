package com.web.authentication.filter;

import com.model.domain.Authority;
import com.model.domain.User;
import com.service.IUserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by allan on 28/07/16.
 */
@Slf4j
public class AutenticacaoFilter extends UsernamePasswordAuthenticationFilter {

    @Getter
    @Setter
    private String mensagem;

    @Inject
    private IUserService userService;

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");

        try {
            if (this.validationUser(username, password)) {
                throw new BadCredentialsException(mensagem);
            }
            User user = userService.getUserByUserNameAndPassword(username, password);

            if (user != null) {

                Collection<GrantedAuthority> regras = new ArrayList<GrantedAuthority>();
                for (Authority authority : user.getAuthorities()) {
                    regras.add(new SimpleGrantedAuthority(authority.getAuthority()));
                }

                request.getSession().setAttribute("usuarioLogado", user);
                mensagem = "Bem vindo: " + user.getUsername();
                return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), regras);


            } else {
                mensagem = "Usuário não encontrado!";
                throw new BadCredentialsException(mensagem);
            }

        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        request.getSession().setAttribute("msg", mensagem);
        response.sendRedirect("/pages/entity.xhtml");
    }


    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        request.getSession().setAttribute("msg", mensagem);
        response.sendRedirect("/login.xhtml?erro=true");
    }

    private Boolean validationUser(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            mensagem = "Campo usuário não pode estar vazio";
            return true;
        } else if (StringUtils.isEmpty(password)) {
            mensagem = "Campo senha não pode estar vazio";
            return true;
        }
        return false;
    }
}
