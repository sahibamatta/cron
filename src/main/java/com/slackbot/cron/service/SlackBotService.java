package com.slackbot.cron.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.slackbot.cron.dto.SlackBotResponse;
import com.slackbot.cron.enums.Command;

@Service
public class SlackBotService {

	public SlackBotResponse processMessageAndReturnResponse(String eventsText) {

		System.out.println("in validateTextAndDecodeMessage eventsText is: ["+eventsText+"]");

		SlackBotResponse slackBotResponse = new SlackBotResponse();

		slackBotResponse = validateText(eventsText, slackBotResponse);
		
		System.out.println("in validateTextAndDecodeMessage eventsText is: ["+eventsText+"]");

		slackBotResponse.setMessage("Enter help to explore");

		return slackBotResponse;
	}

	private SlackBotResponse validateText(String eventsText, SlackBotResponse slackBotResponse) {
		
		if(Objects.nonNull(eventsText)) {

			String[] textSplit = eventsText.split(" ");

			if(textSplit.length >= 5) {

				slackBotResponse.setMessage("Please enter a option. Use help command to know more");
			}

			String cmd = textSplit[0];
			
			slackBotResponse = validateCommand(cmd, slackBotResponse);
		}
		
		return slackBotResponse;
	}

	public SlackBotResponse validateCommand(String command, SlackBotResponse slackbotResponse) {

		System.out.println("in validateCommand command ["+command+"]");

		Command cmd = Command.fromString(command);

		if(Objects.isNull(cmd)) {
			slackbotResponse.setMessage("Invalid command");
		}

		return slackbotResponse;
	}

	/*
	 * public SlackBotResponse validateCommandAndDecodeMessage(String eventsText) {
	 * 
	 * System.out.println("in validateCommandAndDecodeMessage ----");
	 * 
	 * if(Objects.nonNull(eventsText)) { SlackBotRequestText slackBotRequestText =
	 * gson.fromJson(eventsText, SlackBotRequestText.class);
	 * 
	 * String command= slackBotRequestText.getCommand();
	 * 
	 * }
	 * 
	 * }
	 */

	public void processMessageAndSaveForScheduling(String eventsText)
	{
		System.out.println("in processMessageAndSaveForScheduling ----");
		if(Objects.nonNull(eventsText)) {
			
		}
	}

}
