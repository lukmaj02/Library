package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Employee;
import com.biblioteka.Library.Exceptions.EmployeeNotFoundException;
import com.biblioteka.Library.Repository.EmployeeRepository;
import com.biblioteka.Library.dto.EmployeeRequest;
import com.biblioteka.Library.dto.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class EmployeeService {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public List<EmployeeResponse> getEmployee(){
        //return jdbcTemplate.query("select * from pracownik",BeanPropertyRowMapper.newInstance(Employee.class));
        return modelMapper.map(employeeRepository.findAll(), new TypeToken<List<EmployeeResponse>>() {}.getType());
    }

    public EmployeeResponse getEmployee(Integer id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return modelMapper.map(employee, EmployeeResponse.class);
    }

    public void addEmployee(EmployeeRequest employeeRequest) {
//        jdbcTemplate.update("call addEmployee(?,?,?,?,?)",employee.getImie(), employee.getNazwisko(),
//                employee.getWiek(),employee.getEmail(), employee.getTelefon());
        Employee employee = modelMapper.map(employeeRequest, Employee.class);
        employeeRepository.save(employee);
    }

    public void changeEmployee(EmployeeRequest employeeRequest, Integer id) {
//        jdbcTemplate.update("call modifyEmployee(?,?,?,?,?,?)",id, employee.getImie(), employee.getNazwisko(),
//                employee.getWiek(),employee.getEmail(), employee.getTelefon());
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employee.setImie(employeeRequest.getImie());
        employee.setNazwisko(employeeRequest.getNazwisko());
        employee.setEmail(employeeRequest.getEmail());
        employee.setTelefon(employeeRequest.getTelefon());
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        //jdbcTemplate.update("delete from pracownik where id=?", id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        employeeRepository.delete(employee);
    }
}
