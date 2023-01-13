package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    @Query(value = "SELECT * FROM employee JOIN user ON employee.id = user.id WHERE employee.email = ?1 AND user.password = ?2", nativeQuery = true)
    Employee findAccount(String email, String password);
}
