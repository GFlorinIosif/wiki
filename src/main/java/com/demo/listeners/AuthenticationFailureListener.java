package com.demo.listeners;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private static final Logger LOG = Logger.getLogger(String.valueOf(AuthenticationFailureListener.class));

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        Object userName = event.getAuthentication().getPrincipal();
        Object credentials = event.getAuthentication().getCredentials();
        LOG.info("Failed login using USERNAME [" + userName + "]");
        LOG.info("Failed login using PASSWORD [" + credentials + "]");
    }
}
