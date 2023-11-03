package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.AdminService;
import com.biblioteka.Library.dto.Mapper.UserMapper;
import com.biblioteka.Library.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserDto> getAll() {
        return adminService.getEmployees()
                .stream()
                .map(UserMapper::map)
                .toList();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto getById(@PathVariable UUID id){
        return UserMapper.map(adminService.getEmployeeById(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable UUID id){
        adminService.deleteEmployee(id);
    }

    @GetMapping("name")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserDto> getByName(@RequestParam("name") String name) {
        return adminService.getUsersByName(name)
                .stream()
                .map(UserMapper::map)
                .toList();
    }
}

