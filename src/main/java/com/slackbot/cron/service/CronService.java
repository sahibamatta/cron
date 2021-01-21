package com.slackbot.cron.service;

import com.slackbot.cron.model.CronDetails;
import com.slackbot.cron.model.CronDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CronService {

    @Autowired
    private CronDetailsRepository cronDetailsRepository;

    public void saveDetails (CronDetails cronDetails){
       cronDetailsRepository.save(cronDetails);
    }

    public void getActiveRows(){
      cronDetailsRepository.getActiveURL("active");
    }

    public List<CronDetails> getAll(){
        List<CronDetails> cronDetailsList = new ArrayList<>();
        cronDetailsRepository.findAll().forEach(cronDetailsList::add);
        return cronDetailsList;

    }
}
