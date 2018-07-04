package com.demo.handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.config.CORSFilter;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.demo.entities.User;
import com.demo.repositories.UserRepository;

@Component
public class CustomAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger LOG = Logger.getLogger(String.valueOf(CustomAuthSuccessHandler.class));

	@Autowired
	UserRepository userRepo;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		String username = authentication.getName();
		User loggedUser = userRepo.findByUserName(username);
        Gson gson = new Gson();

        JSONObject userJson = new JSONObject(gson.toJson(loggedUser));
        userJson.remove("password");

        JSONObject responseJson = new JSONObject();
		responseJson.put("sessionId", request.getSession().getId());
		responseJson.put("user", userJson );

        LOG.info("Successfully logged user with USERNAME [" + username + "]");

        PrintWriter out = response.getWriter();
		out.print(responseJson);

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",  CORSFilter.getAllowedOrigins().contains(origin) ? origin : "");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        response.setContentType("application/json");
		out.flush();
	}
}
