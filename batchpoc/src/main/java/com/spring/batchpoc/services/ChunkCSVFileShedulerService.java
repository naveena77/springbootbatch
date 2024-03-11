package com.spring.batchpoc.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ChunkCSVFileShedulerService {

    @Autowired
    JobLauncher jobLauncher;

    @Qualifier("csvFileChunkJob")
    @Autowired
    Job csvFileChunkJob;
    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobStarter(){
        JobParametersBuilder jobParameters = new JobParametersBuilder();
        jobParameters.addString("time", String.valueOf(System.currentTimeMillis()));
        JobParameters newParameters = jobParameters.toJobParameters();
        try{
            JobExecution jobExecution = jobLauncher.run(csvFileChunkJob,newParameters);
            System.out.println("jobExecution Id"+jobExecution.getId());
        }catch (Exception exception){
            System.out.println(exception.getMessage().toString());
        }
    }
}
