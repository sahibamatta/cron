package com.slackbot.cron.service;

import com.slackbot.cron.model.CronDetails;
import com.slackbot.cron.service.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {

    @Autowired
    CronService c;

//    public Scheduler()
//    {
//        c = new CronService();
//    }

    @Scheduled(fixedRate = 5000, initialDelay = 20000)
    public void runGet()
    {
        findAndRunCrons();
    }

    public void findAndRunCrons()
    {
        List<CronDetails> activeRows = c.getActiveURL();

        if(activeRows != null && activeRows.size() > 0)
        {
            System.out.println(activeRows.size());

            for (CronDetails cron : activeRows)
            {
                System.out.println(cron.getEndPoint());

                RestClient t = new RestClient();
                if (cron.getMethod().compareTo("get") == 0) {
                    String body = t.get(cron.getEndPoint(), "", "");
                    System.out.println(body);
                }
                if (cron.getMethod().compareTo("post") == 0) {
                    String body = t.post(cron.getEndPoint(), "text/plain", "");
                    System.out.println(body);
                }
            }
        }

        c.updateCronDetails(activeRows);
    }
}