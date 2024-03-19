package com.springreader.xlsxtodatasource.bacthconfig.mappers;

import com.springreader.xlsxtodatasource.conversionservice.poiexcelreadeandrwriter.CellFactoryPoiService;
import com.springreader.xlsxtodatasource.conversionservice.poiexcelreadeandrwriter.RowMapperService;
import com.springreader.xlsxtodatasource.domain.Employee;
import org.apache.poi.ss.usermodel.Row;


public class EmployeeItemRowMapper extends CellFactoryPoiService implements RowMapperService<Employee> {

    @Override
    public Employee transformerRow(Row row) {
        Employee employee = new Employee();
        employee.setId((Integer) getCellValue(row.getCell(0)));
        employee.setFirstName((String) getCellValue(row.getCell(1)));
        employee.setLastName((String) getCellValue(row.getCell(2)));
        employee.setEmployeeNumber((String) getCellValue(row.getCell(3)));
        employee.setEmail((String) getCellValue(row.getCell(4)));

        return employee;
    }
}
