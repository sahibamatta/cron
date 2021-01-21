package com.slackbot.cron.dto;

import java.time.LocalTime;

public class SlackBotRequestDto {
	
	@Override
	public String toString() {
		return "SlackBotRequestDto [method=" + method + ", route=" + route + ", startTime=" + startTime + ", frequency="
				+ frequency + "]";
	}
	private String method;
	private String route;
	private LocalTime startTime;
	private int frequency;
	
	public SlackBotRequestDto() {
		
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
