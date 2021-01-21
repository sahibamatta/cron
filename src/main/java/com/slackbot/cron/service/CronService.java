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

    public List<CronDetails> getActiveURL(){

        List<CronDetails> cronDetailsList = cronDetailsRepository.getActiveURL("active");

        return cronDetailsList;
    }

    // Amrendra - This function will accept list of active cron details and we
    // need to update prev_run_time, next_run_time, number_of_runs, status
    public void updateCronDetails(List<CronDetails> cronDetailsList) {


    }

    public List<CronDetails> getAll(){
        List<CronDetails> cronDetailsList = new ArrayList<>();
        cronDetailsRepository.findAll().forEach(cronDetailsList::add);
        return cronDetailsList;

    }

    private CronDetails createCronDetails (String url, String method, String repeatDuration){

        CronDetails cronDetails = new CronDetails();
        cronDetails.setEndPoint("http://google.com");
        cronDetails.setMethod("get");
        LocalTime currentTime = LocalTime.now();
        LocalTime cronEndTime = currentTime.plusHours(2);
        LocalTime cronStartTime = currentTime.plusMinutes(2);
        cronDetails.setCronStartTime(cronStartTime);
        cronDetails.setPrevRunTime(cronStartTime);
        LocalTime nextRepeatDurationHour = getNextRunTime(repeatDuration, cronStartTime);
        cronDetails.setNextRunTime(nextRepeatDurationHour);
        cronDetails.setCronEndTime(cronEndTime);
        cronDetails.setRepeatDuration(repeatDuration);
        cronDetails.setNumberOfRuns(100);
        cronDetails.setStatus("active");
        cronDetails.setMaxNumberOfRuns(20);

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




}
