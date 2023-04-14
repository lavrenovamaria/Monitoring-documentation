package com.example.monitoring.configuration;

import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail websiteMonitoringJob() {
        return newJob(WebsiteMonitoringJob.class)
                .withIdentity("websiteMonitoringJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger websiteMonitoringTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(30)
                .repeatForever();

        return newTrigger()
                .forJob(websiteMonitoringJob())
                .withIdentity("websiteMonitoringTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public StdSchedulerFactory schedulerFactory() {
        return new StdSchedulerFactory();
    }
}
