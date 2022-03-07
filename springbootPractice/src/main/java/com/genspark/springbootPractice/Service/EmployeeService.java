package com.genspark.springbootPractice.Service;

import com.genspark.springbootPractice.DAO.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeService {
    public List<Employee> getListofEmployees();
    public Employee getEmployee(long employeeId);
    public Employee addEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);
    public String deleteEmployee(long employeeId);
}
