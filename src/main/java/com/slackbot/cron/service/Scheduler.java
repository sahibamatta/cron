package com.slackbot.cron.service;

import com.slackbot.cron.service.RestClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(fixedRate = 5000)
    public void runGet()
    {
        RestClient t =  new RestClient();
        String body = t.get("http://localhost:3000", "/fa1d24a8-a5e7-4086-af63-498abd609bf3", "", "");
        System.out.println(body);
    }
}