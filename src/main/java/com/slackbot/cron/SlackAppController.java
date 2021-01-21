package com.slackbot.cron;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;
import javax.servlet.annotation.WebServlet;

@WebServlet("/slack/events")
public class SlackAppController extends SlackAppServlet {
    /**
   *
   */
  private static final long serialVersionUID = 1L;

  public SlackAppController(App app) {
    super(app);
  }
}