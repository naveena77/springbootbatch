package com.spring.batchpoc.services;

import com.spring.batchpoc.domain.RequestJobParams;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    JobLauncher jobLauncher;
    @Qualifier("firstJob")
    @Autowired
    Job firstJob;
    @Qualifier("secondJob")
    @Autowired
    Job secondJob;
    @Async
    public void startJob(String jobName, List<RequestJobParams> requestJobParamsList){
        JobParametersBuilder jobParameters = new JobParametersBuilder();
        requestJobParamsList.stream().forEach(jobParameter -> {
            jobParameters.addString(jobParameter.getParamKey(), jobParameter.getParamValue());
        });
        jobParameters.addString("time", String.valueOf(System.currentTimeMillis()));
        JobParameters newParameters = jobParameters.toJobParameters();
        try{
            JobExecution jobExecution = null;
            if(jobName.equals("First Job")){
                jobLauncher.run(firstJob,newParameters);
            } else if(jobName.equals("Second Job")){
                jobLauncher.run(secondJob,newParameters);
            }
            System.out.println("jobExecution Id"+jobExecution.getId());
        }catch (Exception exception){
           System.out.println(exception.getMessage().toString());
        }

    }
}
