package com.springreader.xlsxtodatasource.services;


import com.springreader.xlsxtodatasource.domain.Employee;

import java.util.List;

public interface EmployeeService {

    /**
     * @param empNumber employee number
     * @return employee
     */
    List<Employee> getByEmployeeNumber(String empNumber);
}