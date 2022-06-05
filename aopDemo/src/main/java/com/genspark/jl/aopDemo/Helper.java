package com.genspark.jl.aopDemo;

import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class Helper {

    @Before("execution(public void test())")
    public void log(){
        System.out.println("Logging");
    }

    @After("execution(public void test())")
    public void transaction(){
        System.out.println("DB commit");
    }
}
