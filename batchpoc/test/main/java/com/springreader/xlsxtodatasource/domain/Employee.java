package com.springreader.xlsxtodatasource.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer id;

    private String firstName;

    private String lastName;

    private String employeeNumber;

    private String email;
}