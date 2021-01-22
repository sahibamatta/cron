package com.slackbot.cron.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CronDetailsRepository extends CrudRepository<CronDetails,Integer> {

    @Query(value="select * from cron_details where status ='active' and " +
            "CURRENT_TIME() >= cron_details.next_run_time and " +
            "cron_details.number_of_runs < cron_details.max_number_of_runs;" , nativeQuery=true)
    List<CronDetails> getActiveURL();


}