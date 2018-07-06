package com.demo.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAuthEntryPoint extends LoginUrlAuthenticationEntryPoint {

    public LoginAuthEntryPoint() {
        super("/login");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (!request.isRequestedSessionIdValid()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Request Denied (Session Expired)");
        } else {
            super.commence(request, response, authException);
        }
    }
}
