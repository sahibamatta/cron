package com.slackbot.cron.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "cron_details")
public class CronDetails {

    @Id @GeneratedValue
    private Integer id;
    private String endPoint;
    private String method;
    private String repeatDuration;
    @Column(columnDefinition = "TIME")
    private LocalTime nextRunTime;
    @Column(columnDefinition = "TIME")
    private LocalTime prevRunTime;
    @Column(columnDefinition = "TIME")
    private LocalTime cronStartTime;
    @Column(columnDefinition = "TIME")
    private LocalTime cronEndTime;
    private String status;
    private int numberOfRuns;
    private int maxNumberOfRuns;


    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRepeatDuration() {
        return repeatDuration;
    }

    public void setRepeatDuration(String repeatDuration) {
        this.repeatDuration = repeatDuration;
    }

    public LocalTime getNextRunTime() {
        return nextRunTime;
    }

    public void setNextRunTime(LocalTime nextRunTime) {
        this.nextRunTime = nextRunTime;
    }

    public LocalTime getPrevRunTime() {
        return prevRunTime;
    }

    public void setPrevRunTime(LocalTime prevRunTime) {
        this.prevRunTime = prevRunTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfRuns() {
        return numberOfRuns;
    }

    public void setNumberOfRuns(int numberOfRuns) {
        this.numberOfRuns = numberOfRuns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getCronStartTime() {
        return cronStartTime;
    }

    public void setCronStartTime(LocalTime cronStartTime) {
        this.cronStartTime = cronStartTime;
    }

    public LocalTime getCronEndTime() {
        return cronEndTime;
    }

    public void setCronEndTime(LocalTime cronEndTime) {
        this.cronEndTime = cronEndTime;
    }

    public int getMaxNumberOfRuns() {
        return maxNumberOfRuns;
    }

    public void setMaxNumberOfRuns(int maxNumberOfRuns) {
        this.maxNumberOfRuns = maxNumberOfRuns;
    }
}