package com.genspark.springbootPractice.Controller;

import com.genspark.springbootPractice.DAO.Employee;
import com.genspark.springbootPractice.Service.EmployeeService;
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

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeFromId(@PathVariable String employeeId){
        return this.employeeService.getEmployee(Long.parseLong(employeeId));
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        return this.employeeService.addEmployee(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){return this.employeeService.addEmployee(employee);}

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){return this.employeeService.deleteEmployee(Long.parseLong(employeeId));}
}
