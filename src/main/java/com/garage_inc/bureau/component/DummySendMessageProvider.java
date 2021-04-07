package com.garage_inc.bureau.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DummySendMessageProvider implements SendMessageProvider {

    private static final Logger log = LoggerFactory.getLogger(DummySendMessageProvider.class);

    @Override
    public void send(String phoneNumber, String message) {
        log.info("Sending message to: " + phoneNumber + " message: " + message);
    }
}
