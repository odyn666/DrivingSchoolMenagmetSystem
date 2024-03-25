package sda.projkonc.smstest.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Config {

    @Value("${twilio.creds.twilioaccountsid}")
    private String sid;

    @Value("${twilio.creds.twilioauthtoken}")
    private String token;
    }
