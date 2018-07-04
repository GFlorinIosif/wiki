package com.demo.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOG = Logger.getLogger(String.valueOf(CustomAuthFailureHandler.class));

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		//response.sendRedirect(request.getContextPath() +  "/login?error");
        response.setContentType("application/json");

        JSONObject responseJson = new JSONObject();
        responseJson.put("error", exception.getMessage());

        LOG.info("Log in error with message: " + exception.getMessage());

        PrintWriter out = response.getWriter();
        out.print(responseJson);
        out.flush();
	}
}
