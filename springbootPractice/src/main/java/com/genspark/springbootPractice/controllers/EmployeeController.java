package com.genspark.springbootPractice.controllers;

import com.genspark.springbootPractice.models.Employee;
import com.genspark.springbootPractice.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String main(){
        return "<HTML>Welcome to My Webpage</HTML>";
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeesList(){
        return employeeService.getListofEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeFromId(@PathVariable String id){
        return this.employeeService.getEmployee(Long.parseLong(id));
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        return this.employeeService.addEmployee(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){return this.employeeService.addEmployee(employee);}

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable String id){return this.employeeService.deleteEmployee(Long.parseLong(id));}
}
