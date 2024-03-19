package com.springreader.xlsxtodatasource.domain;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeItemPreparedStatementSetter implements ItemPreparedStatementSetter<Employee> {
    @Override
    public void setValues(Employee item, PreparedStatement ps) throws SQLException {
        ps.setInt(1,item.getId());
        ps.setString(2,item.getFirstName());
        ps.setString(3,item.getLastName());
        ps.setString(4,item.getEmployeeNumber());
        ps.setString(5,item.getEmail());
    }
}
