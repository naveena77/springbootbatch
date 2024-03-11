package com.spring.batchpoc.config;


import com.spring.batchpoc.listener.FirstJobListener;
import com.spring.batchpoc.listener.FirstStepListener;
import com.spring.batchpoc.processor.FirstItemProcessor;
import com.spring.batchpoc.reader.FirstItemReader;
import com.spring.batchpoc.services.SecondTasklet;
import com.spring.batchpoc.writer.FirstItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SimpleJob {

    @Autowired
    SecondTasklet secondTasklet;

    @Autowired
    FirstJobListener firstJobListener;

    @Autowired
    FirstStepListener firstStepListener;

    @Autowired
    FirstItemReader firstItemReader;

    @Autowired
    FirstItemProcessor firstItemProcessor;

    @Autowired
    FirstItemWriter firstItemWriter;

    @Bean
    public Job firstJob(JobRepository jobRepository,PlatformTransactionManager platformTransactionManager){
        return new JobBuilder("First Job",jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstStep(jobRepository,platformTransactionManager))
                .next(secondStep(jobRepository, platformTransactionManager))
                .listener(firstJobListener)
                .build();
    }

    private Step firstStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("First Step",jobRepository)
               .tasklet(firstTask(),platformTransactionManager)
                .listener(firstStepListener).build();
    }

    private Step secondStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager){
        return new StepBuilder("Second Step",jobRepository)
                .tasklet(secondTasklet,platformTransactionManager).build();
    }

    private Tasklet firstTask(){
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("This is first tasklet  step");
                return  RepeatStatus.FINISHED;
            }
        };
    }
    @Bean
    public Job secondJob(JobRepository jobRepository,PlatformTransactionManager transactionManager){
        return new JobBuilder("Second Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(firstChunkStep(jobRepository,transactionManager))
                .build();
    }

    private Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("First Chunk Step",jobRepository)
                .<Integer,Long>chunk(3, transactionManager)
                .reader(firstItemReader)
                .processor(firstItemProcessor)
                .writer(firstItemWriter)
                .build();
    }
}
