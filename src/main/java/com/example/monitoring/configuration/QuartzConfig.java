package com.example.monitoring.configuration;

import com.example.monitoring.jobs.WebsiteMonitoringJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail monitoringJobDetail() {
        return JobBuilder.newJob(WebsiteMonitoringJob.class)
                .withIdentity("monitoringJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger monitoringJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(monitoringJobDetail())
                .withIdentity("monitoringJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
