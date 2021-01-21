package com.slackbot.cron;

import com.slack.api.bolt.App;
import com.slack.api.bolt.servlet.SlackAppServlet;
import com.slackbot.cron.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.annotation.WebServlet;

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