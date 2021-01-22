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

//        CronDetails cronDetails = createCronDetails(url, method, repeatDuration);

        registerNewCron("http://localhost:3000/fa1d24a8-a5e7-4086-af63-498abd609bf3",
                "get", repeatDuration, LocalTime.now(),
                LocalTime.now().plusHours(2), 100 );

//        cronDetailsRepository.save(cronDetails);
    }

    public List<CronDetails> getActiveURL(){

        List<CronDetails> cronDetailsList = cronDetailsRepository.getActiveURL();
        return cronDetailsList;
    }

    // Amrendra - This function will accept list of active cron details and we
    // need to update prev_run_time, next_run_time, number_of_runs, status
    public void updateCronDetails(List<CronDetails> cronDetailsList) {

        for (CronDetails cron: cronDetailsList)
        {
            cron.setPrevRunTime(cron.getNextRunTime());
            cron.setNextRunTime(getNextRunTime(cron.getRepeatDuration(), cron.getCronStartTime()));
            cron.setNumberOfRuns(cron.getNumberOfRuns() + 1);

            if (cron.getNextRunTime().compareTo(cron.getCronEndTime()) > 0
                    || cron.getNumberOfRuns() >= cron.getMaxNumberOfRuns())
            {
                cron.setStatus("inactive");
            }

            cronDetailsRepository.save(cron);
        }
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

    public void addNewCron(CronDetails cronDetails)
    {
        LocalTime currentTime = LocalTime.now();
        LocalTime cronEndTime = currentTime.plusHours(5);
        LocalTime cronStartTime = currentTime.plusMinutes(2);

        cronDetails.setCronStartTime(cronStartTime);
        cronDetails.setPrevRunTime(cronStartTime);

        LocalTime nextRepeatDurationHour = getNextRunTime(cronDetails.getRepeatDuration(), cronStartTime);
        cronDetails.setNextRunTime(nextRepeatDurationHour);

        cronDetails.setCronEndTime(cronEndTime);

        cronDetails.setNumberOfRuns(0);

        // setting every new cron as active even if it's start time is not reached yet
        // as nextRunTime will handle it
        cronDetails.setStatus("active");

        cronDetails.setMaxNumberOfRuns(100);

        cronDetailsRepository.save(cronDetails);
    }

    public void registerNewCron(
            String endPoint,
            String method,
            String repeatDuration,
            LocalTime startTime,
            LocalTime endTime,
            int maxNumberOfRuns
    )
    {
        CronDetails cronDetails = new CronDetails();

        cronDetails.setEndPoint(endPoint);
        cronDetails.setMethod(method);

        LocalTime currentTime = LocalTime.now();
        LocalTime cronEndTime = endTime;
        LocalTime cronStartTime = currentTime.plusMinutes(2);

        cronDetails.setCronStartTime(cronStartTime);
        cronDetails.setPrevRunTime(cronStartTime);

        LocalTime nextRepeatDurationHour = getNextRunTime(repeatDuration, cronStartTime);
        cronDetails.setNextRunTime(nextRepeatDurationHour);

        cronDetails.setCronEndTime(cronEndTime);
        cronDetails.setRepeatDuration(repeatDuration);

        cronDetails.setNumberOfRuns(0);

        // setting every new cron as active even if it's start time is not reached yet
        // as nextRunTime will handle it
        cronDetails.setStatus("active");

        cronDetails.setMaxNumberOfRuns(maxNumberOfRuns);

        cronDetailsRepository.save(cronDetails);
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
