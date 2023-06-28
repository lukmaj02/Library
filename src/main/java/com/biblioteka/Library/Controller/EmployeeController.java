package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Employee;
import com.biblioteka.Library.LibraryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {
        @Autowired
        LibraryDatabase libraryDatabase;

        @GetMapping("")
        public List<Employee> showEmployees(){
            return libraryDatabase.getEmployee();
        }

        @GetMapping("/{id}")
        public Employee showEmployee(@PathVariable Integer id){
            return libraryDatabase.getEmployee(id);
        }

        @PostMapping()
        public void addEmployee(@RequestBody Employee employee){
            libraryDatabase.addEmployee(employee);
        }


    }

