package com.slackbot.cron;

import javax.servlet.annotation.WebServlet;

import org.springframework.web.bind.annotation.RestController;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;

@WebServlet("/slack/events")
@RestController
public class SlackAppController extends SlackAppServlet {
    /**
   *
   */
  private static final long serialVersionUID = 1L;

  public SlackAppController(App app) {
      super(app);
  }


}