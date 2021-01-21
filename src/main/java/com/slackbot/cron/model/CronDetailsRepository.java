package com.slackbot.cron.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CronDetailsRepository extends CrudRepository<CronDetails,String> {

    @Query(value="select * from cron_details where status := status ", nativeQuery=true)
    List<CronDetails> getActiveURL(String status);

}
