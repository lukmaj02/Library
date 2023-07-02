package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Entity.Employee;
import com.biblioteka.Library.Exceptions.EmployeeNotFoundException;
import com.biblioteka.Library.Service.EmployeeService;
import com.biblioteka.Library.dto.EmployeeRequest;
import com.biblioteka.Library.dto.EmployeeResponse;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
    @RestController
    @RequestMapping("/employee")
    public class EmployeeController implements IController<EmployeeRequest, EmployeeResponse> {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

        @Override
        @GetMapping("")
        @ResponseStatus(HttpStatus.OK)
        public List<EmployeeResponse> getAll(){
            return employeeService.getEmployee();
        }

        @Override
        @GetMapping("/{id}")
        @ResponseStatus(HttpStatus.OK)
        public EmployeeResponse getById(@PathVariable Integer id){
            return employeeService.getEmployee(id);
        }
        @Override
        @PostMapping()
        @ResponseStatus(HttpStatus.CREATED)
        public void add(@RequestBody EmployeeRequest employeeRequest){
            employeeService.addEmployee(employeeRequest);
        }
        @Override
        @PutMapping("/{id}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void changeById(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest){
            employeeService.changeEmployee(employeeRequest, id);
        }
        @Override
        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.ACCEPTED)
        public void deleteById(@PathVariable Integer id){
            employeeService.deleteEmployee(id);
        }
    }

