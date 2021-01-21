package com.slackbot.cron;

import com.slack.api.bolt.App;

import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.MessageEvent;
import com.slackbot.cron.dto.SlackBotResponse;
import com.slackbot.cron.service.SlackBotService;

import org.springframework.beans.factory.annotation.Autowired;
// import com.slack.api.methods.response.chat.ChatPostMessageResponse;
// import com.slack.api.model.event.MessageEvent;
import com.slack.api.model.event.AppMentionEvent;
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

        app.command("/cronbot", (req, ctx) -> {
            System.out.println(req);
            SlackBotResponse slackBotResponse = slackBotService.processMessageAndReturnResponse(req.getPayload().getText());
            return ctx.ack(slackBotResponse.toString());
        });

		/*
		 * app.event(MessageEvent.class, (payload, ctx) -> {
		 * System.out.println(payload);
		 * 
		 * MessageEvent event = payload.getEvent();
		 * slackBotService.processMessageAndSaveForScheduling(event.getText());
		 * 
		 * ChatPostMessageResponse message = ctx.client().chatPostMessage(r -> r
		 * 
		 * MessageEvent event = payload.getEvent(); ChatPostMessageResponse message =
		 * ctx.client().chatPostMessage(r -> r .channel(event.getChannel())
		 * .threadTs(event.getTs()) .text(s));
		 * 
		 * if (!message.isOk()) { ctx.logger.error("chat.postMessage failed: {}",
		 * message.getError()); }
		 * 
		 * return ctx.ack(); });
		 */

        app.event(AppMentionEvent.class, (payload, ctx) -> {
            System.out.println(payload);
            
            SlackBotResponse slackBotResponse = slackBotService.processMessageAndReturnResponse(payload.getEvent().getText());
            
            AppMentionEvent event = payload.getEvent();
                ctx.client().chatPostMessage(r -> r
                .channel(event.getChannel())
                .threadTs(event.getTs())
                .text(slackBotResponse.toString()));

            return ctx.ack();
        });


        return app;
    }
}
