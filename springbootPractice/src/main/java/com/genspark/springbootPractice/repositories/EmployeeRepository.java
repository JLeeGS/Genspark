package com.genspark.springbootPractice.repositories;

import com.genspark.springbootPractice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findEmployeeById(long id);
}
