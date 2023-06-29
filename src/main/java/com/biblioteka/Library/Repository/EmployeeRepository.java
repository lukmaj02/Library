package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Employee findByName();
    Employee findByUsername();

}

