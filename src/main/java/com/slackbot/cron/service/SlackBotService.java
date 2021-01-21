package com.slackbot.cron.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.slackbot.cron.dto.SlackBotRequestDto;
import com.slackbot.cron.dto.SlackBotResponseDto;
import com.slackbot.cron.enums.Command;
import com.slackbot.cron.model.CronDetails;

@Service
public class SlackBotService {

	public String processMessageAndReturnResponse(String eventsText) {

		System.out.println("in validateTextAndDecodeMessage eventsText is: ["+eventsText+"]");

		SlackBotResponseDto slackBotResponse = new SlackBotResponseDto();

		slackBotResponse = validateTextAndGenerateResponse(eventsText, slackBotResponse);

		System.out.println("SlackBotResponse is ["+slackBotResponse.toString()+"]");

		return slackBotResponse.getMessage();
	}

	private SlackBotResponseDto validateTextAndGenerateResponse(String eventsText, SlackBotResponseDto slackBotResponse) {

		System.out.println("in validateTextAndGenerateResponse");

		if(Objects.nonNull(eventsText)) {

			String[] textSplit = eventsText.split(" ");

			String cmd = textSplit[0];

			slackBotResponse = validateCommand(cmd, slackBotResponse);

			if(!slackBotResponse.getMessage().equals("")) {
				return slackBotResponse;
			}

			slackBotResponse = generateResponse(cmd, eventsText, slackBotResponse);

			if(!slackBotResponse.getMessage().equals("")) {
				return slackBotResponse;
			}
		}

		else {

			slackBotResponse.setMessage("Enter help to explore");
		}

		return slackBotResponse;
	}

	private SlackBotResponseDto validateCommand(String command, SlackBotResponseDto slackbotResponse) {

		System.out.println("in validateCommand command ["+command+"]");

		Command cmd = Command.fromString(command);

		if(Objects.isNull(cmd)) {
			slackbotResponse.setMessage("Invalid command");
		}

		return slackbotResponse;
	}

	private SlackBotResponseDto generateResponse(String cmd, String eventText, SlackBotResponseDto slackBotResponse) {

		Command command = Command.fromString(cmd);
		String message = "";

		switch (command) {
		case HELP:
			message = helpMessage();
			break;

		case LIST:
			message = listCommandMessage();
			break;

		case CREATE:
			message = createCronMessage(eventText);
			break;
		}

		slackBotResponse.setMessage(message);
		return slackBotResponse;
	}

	private String helpMessage() {
		System.out.println("in helpMessage");

		String message = "Help Section!!!!";
		return message;
	}

	private String listCommandMessage() {
		System.out.println("in listCommandMessage");

		String line1 = "Help";
		String line2 = "List";
		String line3 = "Create";

		String message = line1 + "\r\n" + line2 + "\r\n" + line3;

		return message;
	}

	private String createCronMessage(String eventText) {
		System.out.println("in createCronMessage");

		return createSchedulerAndReturnMessage(eventText);
	}

	private String createSchedulerAndReturnMessage(String eventText) {
		System.out.println("in convertToSlackbotRequestObject");

		String [] textSplit = eventText.split(" ");

		if(textSplit.length != 5) {
			return "Please enter correct format. Refer help section";
		}

		SlackBotRequestDto slackBotRequestDto = new SlackBotRequestDto();

		slackBotRequestDto.setMethod(textSplit[1]);
		slackBotRequestDto.setRoute(textSplit[2]);

		try {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime localTime = LocalTime.parse(textSplit[3], formatter);
			slackBotRequestDto.setStartTime(localTime);
		}

		catch(Exception e) {
			System.out.println("Starttime exception is:"+e);
			return "Incorrect start time format. Valid format is HH:mm:ss";
		}

		try {
			int frequency = Integer.parseInt(textSplit[4]);
			slackBotRequestDto.setFrequency(frequency);
		}

		catch(Exception e) {
			System.out.println("Frequency exception is:"+e);
			return "Incorrect frequency format. Frequency must be integer";
		}

		CronDetails cronDetails = dtoToEntity(slackBotRequestDto);

		//TODO: entity create call -- put in try/catch

		return "Woohoo! Scheduler Configured Successfully";

	}

	private CronDetails dtoToEntity(SlackBotRequestDto slackBotRequestDto) {

		System.out.println("in dtoToEntity, dto is ["+slackBotRequestDto.toString()+"]");
		CronDetails cronDetails = new CronDetails();
		cronDetails.setMethod(slackBotRequestDto.getMethod());
		cronDetails.setEndPoint(slackBotRequestDto.getRoute());
		cronDetails.setCronStartTime(slackBotRequestDto.getStartTime());
		cronDetails.setRepeatDuration(slackBotRequestDto.getFrequency()+"");

		return cronDetails;
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
