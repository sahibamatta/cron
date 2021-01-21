package com.slackbot.cron;

import com.slack.api.bolt.App;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.MessageEvent;
import com.slackbot.cron.service.SlackBotService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackApp {
	
	@Autowired
	private SlackBotService slackBotService;
	
    @Bean
    public App initSlackApp() {
        //logging.level.com.slack.api = DEBUG
        App app  = new App();

        app.command("/hello", (req, ctx) -> {
            System.out.println(req);
            return ctx.ack("Hello to you!!");
        });

        app.event(MessageEvent.class, (payload, ctx) -> {
            System.out.println(payload);
            
            MessageEvent event = payload.getEvent();
            slackBotService.processMessageAndSaveForScheduling(event.getText());
            
            ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
                .channel(event.getChannel())
                .threadTs(event.getTs())
                .text("Hey"));

            if (!message.isOk()) {
                ctx.logger.error("chat.postMessage failed: {}", message.getError());
            }

            return ctx.ack();
        });
        return app;
    }
}
