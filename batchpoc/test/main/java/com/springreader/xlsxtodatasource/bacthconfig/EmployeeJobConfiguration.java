package com.springreader.xlsxtodatasource.bacthconfig;

import com.springreader.xlsxtodatasource.bacthconfig.listeners.JobCompletionListener;
//import com.springreader.xlsxtodatasource.bacthconfig.processors.EmployeeItemProcessor;
import com.springreader.xlsxtodatasource.bacthconfig.reader.EmployeeItemReader;
import com.springreader.xlsxtodatasource.bacthconfig.validators.EmployeeJobParametersValidator;
import com.springreader.xlsxtodatasource.domain.Employee;
import com.springreader.xlsxtodatasource.domain.EmployeeItemPreparedStatementSetter;
import org.hibernate.SessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collections;

/**
 * Configuration for batch
 */

@Configuration
public class EmployeeJobConfiguration {

    @Autowired
    public DataSource dataSource;

    @Autowired
    JobCompletionListener jobCompletionListener;

    @Bean
    public JobParametersValidator jobParametersValidator() {
        return new EmployeeJobParametersValidator();
    }

    /**
     * job declaration
     * @param  {@link JobCompletionListener}
     * @return {@link Job}
     */
    @Bean
    public Job employeeJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("employeeJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobCompletionListener)
                .start(employeeStep(jobRepository,transactionManager))
                .validator(compositeJobParametersValidator())
                .build();
    }

    /**
     * step declaration
     * @return {@link Step}
     */
    @Bean
    public Step employeeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("employeeStep",jobRepository)
                .<Employee, Employee>chunk(3, transactionManager)
                .reader(itemReader())
                //.processor(itemProcessor())
                .writer(employeeJdbcBatchItemWriter())
                .build();
    }


    @Bean
    public ItemReader<Employee> itemReader() {
        return new EmployeeItemReader();
    }

//    @Bean
//    public ItemProcessor<Employee, Employee> itemProcessor() {
//        return new EmployeeItemProcessor();
//    }

    @Bean
    public JdbcBatchItemWriter<Employee> employeeJdbcBatchItemWriter(){
        JdbcBatchItemWriter<Employee> jdbcBatchItemWriter = new JdbcBatchItemWriter<Employee>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql( "insert into employee values (?,?,?,?,?)");
        jdbcBatchItemWriter.setItemPreparedStatementSetter(new EmployeeItemPreparedStatementSetter());
        return jdbcBatchItemWriter;
    }

    @Bean
    public JobParametersValidator compositeJobParametersValidator() {
        CompositeJobParametersValidator bean = new CompositeJobParametersValidator();
        bean.setValidators(Collections.singletonList(jobParametersValidator()));
        return bean;
    }

}
