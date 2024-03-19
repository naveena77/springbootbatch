//package com.springreader.xlsxtodatasource.services;
//
//import com.springreader.xlsxtodatasource.domain.Employee;
//import com.springreader.xlsxtodatasource.repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//
//    @Autowired
//    private  EmployeeRepository employeeRepository;
//
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
//        this.employeeRepository = employeeRepository;
//    }
//
//    @Override
//    public List<Employee> getByEmployeeNumber(String empNumber) {
//        return employeeRepository.findByEmployeeNumber(empNumber);
//    }
//
//}