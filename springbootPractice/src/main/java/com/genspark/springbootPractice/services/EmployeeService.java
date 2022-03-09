package com.genspark.springbootPractice.services;

import com.genspark.springbootPractice.models.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getListofEmployees();
    public Employee getEmployee(long id);
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public String deleteEmployee(long id);
}
