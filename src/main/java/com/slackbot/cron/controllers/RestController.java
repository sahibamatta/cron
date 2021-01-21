package com.slackbot.cron.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("start")
public class RestController {

	@GetMapping("hello")
	public String hello() {
		return "hello";
	}

}
