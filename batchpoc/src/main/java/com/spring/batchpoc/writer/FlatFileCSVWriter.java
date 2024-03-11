package com.spring.batchpoc.writer;


import com.spring.batchpoc.domain.EmployeeCSV;
import com.spring.batchpoc.domain.EmployeeItemPreparedStatementSetter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class FlatFileCSVWriter{

    @Autowired
    public DataSource dataSource;

    @Bean
    public JdbcBatchItemWriter<EmployeeCSV> employeeJdbcBatchItemWriter(){
        JdbcBatchItemWriter<EmployeeCSV> jdbcBatchItemWriter = new JdbcBatchItemWriter<EmployeeCSV>();
        jdbcBatchItemWriter.setDataSource(dataSource);
        jdbcBatchItemWriter.setSql( "insert into employee values (?,?,?,?)");
        jdbcBatchItemWriter.setItemPreparedStatementSetter(new EmployeeItemPreparedStatementSetter());
        return jdbcBatchItemWriter;
    }

}
