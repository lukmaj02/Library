package com.biblioteka.Library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.JDBCType;
import java.util.List;
@Configuration
public class LibraryDatabase {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Employee> getEmployee(){
        return jdbcTemplate.query("select * from pracownik", BeanPropertyRowMapper.newInstance(Employee.class));
    }

    public Employee getEmployee(Integer id){
        Employee employee = jdbcTemplate.queryForObject("select * from pracownik where id = ?",BeanPropertyRowMapper.newInstance(Employee.class),id);
        return employee;
    }

    public void addEmployee(Employee employee) {
        jdbcTemplate.update("call AddEmployee(?,?,?,?,?)",employee.getImie(), employee.getNazwisko(), employee.getWiek(),employee.getEmail(), employee.getTelefon());
    }


}
