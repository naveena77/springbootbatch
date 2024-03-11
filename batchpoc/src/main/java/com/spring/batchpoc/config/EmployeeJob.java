package com.spring.batchpoc.config;


import com.spring.batchpoc.domain.EmployeeCSV;
import com.spring.batchpoc.services.FlatFileCSVReaderService;
import com.spring.batchpoc.writer.FlatFileCSVWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class EmployeeJob {

    @Autowired
    private FlatFileCSVWriter flatFileCSVWriter;

    @Autowired
    private FlatFileCSVReaderService flatFileCSVReader;

    @Bean
    public Job csvFileChunkJob(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        return new JobBuilder("CSV File Chunk Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(csvFileChunkStep(jobRepository,transactionManager))
                .build();
    }

    private Step csvFileChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("Csv File Chunk Step",jobRepository)
                .<EmployeeCSV,EmployeeCSV>chunk(3, transactionManager)
                .reader(flatFileCSVReader.flatFileItemReader())
                //.processor(firstItemProcessor)
                .writer(flatFileCSVWriter.employeeJdbcBatchItemWriter())
                .build();
    }

}
