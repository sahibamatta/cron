package com.slackbot.cron;

import com.slackbot.cron.model.CronDetails;
import com.slackbot.cron.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaveController {

    @Autowired
    private CronService cronService;

    @GetMapping("/hello")
    public String sayHello() {
        System.out.println("Reached 123");
		cronService.saveDetails("google.com","get", "01:01:01");
        List<CronDetails> cronDetailsList = cronService.getActiveURL();

        List<CronDetails> cronDetailsListAll = cronService.getAll();
        return "Success";
        // hh:mm:ss - Repeat duration hardcoded format.

    }
}
