package com.example.monitoring.configuration;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.example.monitoring.jobs.WebsiteMonitoringJob;

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

    @SpringBootApplication
    public class MonitoringApplication {

        public static void main(String[] args) {
            SpringApplication.run(MonitoringApplication.class, args);

            QuartzConfig quartzConfig = new QuartzConfig();
            try {
                Scheduler scheduler = quartzConfig.schedulerFactory().getScheduler();
                scheduler.addJob(quartzConfig.websiteMonitoringJob(), true);
                scheduler.scheduleJob(quartzConfig.websiteMonitoringTrigger());
                scheduler.start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
    }
}
