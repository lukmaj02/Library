package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Entity.Employee;
import com.biblioteka.Library.Exceptions.EmployeeNotFoundException;
import com.biblioteka.Library.Service.EmployeeService;
import com.biblioteka.Library.dto.EmployeeRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

@RestController
    @RequestMapping("/employee")
    public class EmployeeController {
        @Autowired
        private EmployeeService employeeService;
        @Autowired
        private ModelMapper modelMapper;

        @GetMapping("")
        public List<EmployeeRequest> showEmployees(){
            List<Employee> employees = employeeService.getEmployee();
            return modelMapper.map(employees, new TypeToken<List<EmployeeRequest>>() {}.getType());
        }

        @GetMapping("/{id}")
        public EmployeeRequest showEmployee(@PathVariable Integer id){
            Employee employee = employeeService.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            return modelMapper.map(employee, EmployeeRequest.class);
        }

        @PostMapping()
        public void addEmployee(@RequestBody EmployeeRequest employeeRequest){
            Employee employee = modelMapper.map(employeeRequest, Employee.class);
            employeeService.addEmployee(employee);
        }

        @PutMapping("/{id}")
        public void changeEmployee(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest){
            employeeService.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            Employee employee = modelMapper.map(employeeRequest, Employee.class);
            employeeService.changeEmployee(employee, id);
        }

        @DeleteMapping("/{id}")
        public void deleteEmployee(@PathVariable Integer id){
            employeeService.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            employeeService.deleteEmployee(id);
        }
    }

