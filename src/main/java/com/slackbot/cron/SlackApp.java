package com.slackbot.cron;

import com.slack.api.bolt.App;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackApp {
    @Bean
    public App initSlackApp() {
        App app  = new App();

        app.command("/hello", (req, ctx) -> {
            System.out.println(req);
            return ctx.ack("Hello to you!!");
        });
        return app;
    }
}
