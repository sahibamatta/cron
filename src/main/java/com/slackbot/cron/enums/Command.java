package com.slackbot.cron.enums;

public enum Command {
	
	HELP("help"),
	LIST("list"),
	CREATE("create");

	private String commandNames;

	Command(String commandNames)
	{
		this.setCommandNames(commandNames);
	}

	public String getCommandNames()
	{
		return commandNames;
	}

	public void setCommandNames(String commandNames)
	{
		this.commandNames = commandNames;
	}
	
	 public static Command fromString(String cmd) {
	        for (Command c : Command.values()) {
	            if (c.commandNames.equalsIgnoreCase(cmd)) {
	                return c;
	            }
	        }
	        return null;
	    }

}
