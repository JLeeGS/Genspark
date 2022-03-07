package com.genspark.springbootPractice.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Employee {
    private long employeeId;
    private String fname, lname, email;

    public long getId() {
        return employeeId;
    }
    public void setId(long employeeId) {
        this.employeeId = employeeId;
    }
    public String getFname() {
        return fname;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public String getLname() {
        return lname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(){
        super();
    }
    public Employee(long employeeId, String fname, String lname, String email) {
        this.employeeId = employeeId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + employeeId +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
