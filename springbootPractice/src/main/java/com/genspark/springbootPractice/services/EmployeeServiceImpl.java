package com.genspark.springbootPractice.services;

import com.genspark.springbootPractice.models.Employee;
import com.genspark.springbootPractice.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getListofEmployees() {
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(long id) {
        Optional<Employee> emp= this.employeeRepository.findById(id);
        Employee employee=null;
        if(emp.isPresent()){
            employee= emp.get();
        }
        else{
            throw new RuntimeException("Employee Not Found "+id);
        }
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public String deleteEmployee(long id) {
        employeeRepository.deleteById(id);
        return "Deleted Employee: "+id;
    }
}