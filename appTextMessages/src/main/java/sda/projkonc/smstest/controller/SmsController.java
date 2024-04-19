package sda.projkonc.smstest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import org.springframework.beans.factory.annotation.Value;
import sda.projkonc.smstest.config.Config;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SmsController {

    private final Config config;

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {

        log.info(config.getSid());
        log.info(config.getToken());

        Twilio.init(config.getSid(), config.getToken());

        PhoneNumber to = new PhoneNumber("+48534296847");
        PhoneNumber from = new PhoneNumber("+16505177842");
        Message.creator(to,
                from, "Chyba Działa").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}