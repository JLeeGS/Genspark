package com.genspark.employeedemo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Employee implements Work {

    private int id;
    private String name;
    private List<String> contact_numbers;
    private Address address;

    @Autowired
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<String> getContact_numbers() {
        return contact_numbers;
    }
    public void setContact_numbers(List<String> contact_numbers) {
        this.contact_numbers = contact_numbers;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    public Employee(){
        super();
    }

    public Employee(int id, String name, List<String> contact_numbers, Address address) {
        this.id = id;
        this.name = name;
        this.contact_numbers = contact_numbers;
        this.address = address;
    }

    @Override
    public String toString(){
        return "Employee: id:"+getId() +" Name: "+ getName()+
                " Contact Numbers "+ getContact_numbers() + " Address "+ getAddress();
    }

    @Override
    public void work() {
        System.out.println("Working Employee" +getAddress() +" "+getContact_numbers());
    }
}
