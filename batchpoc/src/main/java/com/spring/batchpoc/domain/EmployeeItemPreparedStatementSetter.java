package com.spring.batchpoc.domain;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeItemPreparedStatementSetter implements ItemPreparedStatementSetter<EmployeeCSV> {
    @Override
    public void setValues(EmployeeCSV item, PreparedStatement ps) throws SQLException {
        ps.setInt(1,item.getId());
        ps.setString(2,item.getFirstName());
        ps.setString(3,item.getLastName());
        ps.setString(4,item.getEmail());
    }
}
