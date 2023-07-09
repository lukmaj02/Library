package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

    List<Employee> findByName(String name);
}

