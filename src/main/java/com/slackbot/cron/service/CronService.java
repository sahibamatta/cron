package com.slackbot.cron.service;

import com.slackbot.cron.model.CronDetails;
import com.slackbot.cron.model.CronDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CronService {

    @Autowired
    private CronDetailsRepository cronDetailsRepository;


    public void saveDetails (String url, String method, String repeatDuration){

        CronDetails cronDetails = createCronDetails(url, method, repeatDuration);
        cronDetailsRepository.save(cronDetails);
    }

    private CronDetails createCronDetails (String url, String method, String repeatDuration){

        CronDetails cronDetails = new CronDetails();
        cronDetails.setEndPoint("http://google.com");
        cronDetails.setMethod("get");
        LocalTime currentTime = LocalTime.now();
        LocalTime startTime = currentTime.plusMinutes(2);
        cronDetails.setStartTime(startTime);

        LocalTime nextRepeatDurationHour = getNextRunTime(repeatDuration, startTime);
        cronDetails.setNextRunTime(nextRepeatDurationHour);

        cronDetails.setRepeatDuration(repeatDuration);
        cronDetails.setNumberOfRuns(100);
        cronDetails.setStatus("active");

        return cronDetails;
    }

    private LocalTime getNextRunTime(String repeatDuration, LocalTime startTime) {
        int finalRepeatDurationHour = getRepeatDurationDay(repeatDuration.split(":"), 0);
        int finalRepeatDurationMinute = getRepeatDurationDay(repeatDuration.split(":"), 1);
        int finalRepeatDurationSecond = getRepeatDurationDay(repeatDuration.split(":"), 2);

        LocalTime nextRepeatDurationSecond = startTime.plusSeconds(finalRepeatDurationSecond);
        LocalTime nextRepeatDurationMinute = nextRepeatDurationSecond.plusMinutes(finalRepeatDurationMinute);
        LocalTime nextRepeatDurationHour = nextRepeatDurationMinute.plusHours(finalRepeatDurationHour);
        return nextRepeatDurationHour;
    }


    private int getRepeatDurationDay(String[] repeatDuration, int index){

       return Integer.valueOf(repeatDuration[index]);

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
