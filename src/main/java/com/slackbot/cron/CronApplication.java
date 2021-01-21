package com.slackbot.cron;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = IntegrationAutoConfiguration.class)
@ServletComponentScan
@EnableSwagger2
public class CronApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronApplication.class, args);
	}

}
