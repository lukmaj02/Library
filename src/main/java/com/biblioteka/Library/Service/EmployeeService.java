package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Employee;
import com.biblioteka.Library.Exceptions.EmployeeNotFoundException;
import com.biblioteka.Library.Repository.EmployeeRepository;
import com.biblioteka.Library.dto.EmployeeRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Configuration
@Service
public class EmployeeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getEmployee(){
        //return jdbcTemplate.query("select * from pracownik",BeanPropertyRowMapper.newInstance(Employee.class));
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Integer id){
        //return Optional.ofNullable(jdbcTemplate.queryForObject("select * from pracownik where id = ?", BeanPropertyRowMapper.newInstance(Employee.class), id));
        return employeeRepository.findById(id);
    }

    public void addEmployee(Employee employee) {
//        jdbcTemplate.update("call addEmployee(?,?,?,?,?)",employee.getImie(), employee.getNazwisko(),
//                employee.getWiek(),employee.getEmail(), employee.getTelefon());
        employeeRepository.save(employee);
    }

    public void changeEmployee(Employee employee, Integer id) {
//        jdbcTemplate.update("call modifyEmployee(?,?,?,?,?,?)",id, employee.getImie(), employee.getNazwisko(),
//                employee.getWiek(),employee.getEmail(), employee.getTelefon());
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        //jdbcTemplate.update("delete from pracownik where id=?", id);
        employeeRepository.deleteById(id);
    }
}
