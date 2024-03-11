package com.spring.batchpoc.listener;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {


    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Before Step"+ stepExecution.getStepName());
        System.out.println("Job Exe Context"+ stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Step Exe Context"+ stepExecution.getExecutionContext());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Before Step"+ stepExecution.getStepName());
        System.out.println("Job Exe Context"+ stepExecution.getJobExecution().getExecutionContext());
        System.out.println("Step Exe Context"+ stepExecution.getExecutionContext());
        return null;
    }
}
