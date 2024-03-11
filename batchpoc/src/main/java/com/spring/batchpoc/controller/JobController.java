package com.spring.batchpoc.controller;


import com.spring.batchpoc.domain.RequestJobParams;
import com.spring.batchpoc.services.JobService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    JobService jobService;

    @Autowired
    JobOperator jobOperator;
    @GetMapping("/start/{jobName}")
    public String startJob(@PathVariable String jobName,
                           @RequestBody List<RequestJobParams> requestJobParamsList) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
      jobService.startJob(jobName,requestJobParamsList);
      return "Job Started...";
    }

    @GetMapping("/stop/{jobExecutionId}")
    public String stopJob(@PathVariable long jobExecutionId){
        try{
           jobOperator.stop(jobExecutionId);
        }catch(Exception ex){
          return ex.getMessage().toString();
        }
        return "Job Stopped...";
    }
}
