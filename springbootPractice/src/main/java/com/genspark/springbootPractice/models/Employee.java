package com.genspark.springbootPractice.models;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
    @Id
    @Column(name="employeeId", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="fname")
    private String fname;
    @Column(name="lname")
    private String lname;
    @Column(name="email")
    private String email;
    @Column(name="salary")
    private double salary;

    public long getId() {
        return id;
    }
    public void setId(long employeeId) {
        this.id = employeeId;
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
    public double getSalary() {
        return salary;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(){
        super();
    }
    public Employee(long employeeId, String fname, String lname, String email) {
        this.id = employeeId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
