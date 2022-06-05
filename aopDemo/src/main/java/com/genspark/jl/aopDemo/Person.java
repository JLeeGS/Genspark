package com.genspark.jl.aopDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    @Value("#{10}")
    private int x;
    @Value("#{20}")
    private int y;
    @Value("#{person.x+person.y}")
    private int sum;

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public void test(){
        System.out.println("Sum of expression "+ sum);
    }
}
