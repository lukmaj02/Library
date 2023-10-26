package com.biblioteka.Library.Exceptions.NotFoundException;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Integer id){
        super("There is no employee who has id = " + id);
    }

    public EmployeeNotFoundException(String name){
        super("There is no employee named "+ name);
    }

}
