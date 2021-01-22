package com.slackbot.cron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.slackbot.cron.dto.RestRequestDto;
import com.slackbot.cron.service.SlackBotService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private SlackBotService slackBotService;

	@PostMapping("cron")
	public String postCall(@RequestBody RestRequestDto restRequestDto) {

		System.out.println("in postCall restRequestDto ["+restRequestDto+"]");
		return slackBotService.processMessageAndReturnResponse(restRequestDto.getText());

	}
}
