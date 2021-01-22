package com.slackbot.cron.dto;

public class RestRequestDto {
	
	@Override
	public String toString() {
		return "RestRequestDto [text=" + text +"]";
	}
	private String text;
	
	public RestRequestDto() {
		
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
