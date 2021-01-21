package com.slackbot.cron.dto;


public class SlackBotResponseDto {
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public SlackBotResponseDto(){
		
	}
	
	public SlackBotResponseDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String message;

}
