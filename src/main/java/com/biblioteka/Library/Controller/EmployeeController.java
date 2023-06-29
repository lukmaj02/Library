package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Entity.Employee;
import com.biblioteka.Library.Exceptions.EmployeeNotFoundException;
import com.biblioteka.Library.Service.EmployeeService;
import com.biblioteka.Library.dto.EmployeeRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
    @RequestMapping("/employee")
    public class EmployeeController implements IController<EmployeeRequest> {
        @Autowired
        private EmployeeService employeeService;

        @Autowired
        private ModelMapper modelMapper;

        @Override
        @GetMapping("")
        public List<EmployeeRequest> getAll(){
            List<Employee> employees = employeeService.getEmployee();
            return modelMapper.map(employees, new TypeToken<List<EmployeeRequest>>() {}.getType());
        }

        @Override
        @GetMapping("/{id}")
        public EmployeeRequest get(@PathVariable Integer id){
            Employee employee = employeeService.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            return modelMapper.map(employee, EmployeeRequest.class);
        }
        @Override
        @PostMapping()
        public void add(@RequestBody EmployeeRequest employeeRequest){
            Employee employee = modelMapper.map(employeeRequest, Employee.class);
            employeeService.addEmployee(employee);
        }

        @PutMapping("/{id}")
        public void change(@PathVariable Integer id, @RequestBody EmployeeRequest employeeRequest){
            employeeService.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            Employee employee = modelMapper.map(employeeRequest, Employee.class);
            employeeService.changeEmployee(employee, id);
        }

        @DeleteMapping("/{id}")
        public void deleteById(@PathVariable Integer id){
            employeeService.getEmployee(id).orElseThrow(() -> new EmployeeNotFoundException(id));
            employeeService.deleteEmployee(id);
        }
    }

