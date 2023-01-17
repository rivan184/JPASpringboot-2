package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.LoginDTO;
import com.example.demo.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    @Query(value = "SELECT * FROM employee JOIN user ON employee.id = user.id WHERE employee.email = ?", nativeQuery = true)
    Employee findAccount(String email);
    //pake dto, id employee , full name, email, role name

    @Query(value = "SELECT id FROM employee WHERE email = ?",nativeQuery = true)
    Integer getIdByEmail(String email);

}
