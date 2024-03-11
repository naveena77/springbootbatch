package com.spring.batchpoc.services;

import com.spring.batchpoc.domain.EmployeeCSV;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class FlatFileCSVReaderService {
    @StepScope
    @Bean
    public FlatFileItemReader<EmployeeCSV> flatFileItemReader(){
        FlatFileItemReader flatFileItemReader = new FlatFileItemReader<EmployeeCSV>();
        //acceess to the inputfile resource folder location
        flatFileItemReader.setResource(new FileSystemResource(
                new File("D:\\10-3-24\\batchpoc\\src\\main\\InputFiles\\employee.csv")
        ));
        //set the columns by reading using setLineTokenizer and reads the csv line by line
        //by using setFieldSetMapper and map the value in the EmployeeCSV
        flatFileItemReader.setLineMapper(new DefaultLineMapper<EmployeeCSV>(){
            {
                setLineTokenizer(new DelimitedLineTokenizer(){
                    {
                        setNames("ID","First Name","Last Name","Email");
                        //setDelimiter(";");
                    }
                });

                setFieldSetMapper(new BeanWrapperFieldSetMapper<EmployeeCSV>(){
                    {
                        setTargetType(EmployeeCSV.class);
                    }
                });
            }
        });
        //skipping the column header
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }
}
