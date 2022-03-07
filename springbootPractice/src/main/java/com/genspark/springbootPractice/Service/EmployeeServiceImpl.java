package com.genspark.springbootPractice.Service;

import com.genspark.springbootPractice.DAO.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private long employeeId;
    List<Employee> getEmployeeList;

    public EmployeeServiceImpl(){
        getEmployeeList=new ArrayList<>();
        getEmployeeList.add(new Employee(10001, "Johnny", "Smalls", "johnnySmalls@example.com"));
        getEmployeeList.add(new Employee(10002, "Larry", "Bob", "bobbylars@example.com"));
        getEmployeeList.add(new Employee(10003, "Joe", "Land", "landofjoe@example.com"));
        getEmployeeList.add(new Employee(10004, "Will", "Burgers", "willburgers@example.com"));
    }

    @Override
    public List<Employee> getListofEmployees() {
        return getEmployeeList;
    }

    @Override
    public Employee getEmployee(long employeeId) {
        this.employeeId=employeeId;
        Employee employee=null;
        for(Employee getEmp: getEmployeeList){
            if(getEmp.getId()==employeeId){
                employee=getEmp;
                break;
            }
        }
        return employee;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        getEmployeeList.add(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        long employeeId= employee.getId();
        String fname=employee.getFname();
        String lname=employee.getLname();
        String email=employee.getEmail();
        Employee emp=null;
        for(Employee getEmp: getEmployeeList){
            if(getEmp.getId()==employeeId){
                getEmp.setFname(fname);
                getEmp.setLname(lname);
                getEmp.setEmail(email);
                emp=getEmp;
                break;
            }
        }
        return emp;
    }

    @Override
    public String deleteEmployee(long employeeId) {
        for(Employee getEmp: getEmployeeList){
            if(getEmp.getId()==employeeId){
                getEmployeeList.remove(getEmp);
                break;
            }
        }
        return "Deleted Employee: "+employeeId;
    }
}