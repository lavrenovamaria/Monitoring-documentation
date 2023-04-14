package com.example.monitoring.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WebsiteMonitoringJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // Здесь должна быть логика мониторинга веб-сайтов и отправки уведомлений пользователям
    }
}
