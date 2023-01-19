package com.example.demo.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "User")
public class User{
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "password", nullable = false)
    private String password;

    
    @ManyToOne
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role role;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
}
