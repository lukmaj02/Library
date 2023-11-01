package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.EmployeeService;
import com.biblioteka.Library.dto.EmployeeRequest;
import com.biblioteka.Library.dto.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmployeeResponse> getAll() {
        return employeeService.getEmployee();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public EmployeeResponse getById(@PathVariable Integer id){
        return employeeService.getEmployeeById(id);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody EmployeeRequest employeeRequest){
        employeeService.addEmployee(employeeRequest);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeById(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest){
        employeeService.changeEmployee(employeeRequest, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<EmployeeResponse> getByName(@PathVariable String name) {
        return employeeService.getEmployeeByName(name);
    }
}

