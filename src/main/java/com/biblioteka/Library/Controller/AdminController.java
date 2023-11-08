package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.AdminService;
import com.biblioteka.Library.DTO.Mapper.UserMapper;
import com.biblioteka.Library.DTO.UserDto;
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
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAll() {
        return adminService.getUsers()
                .stream()
                .map(UserMapper::map)
                .toList();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getById(@PathVariable UUID id){
        return UserMapper.map(adminService.getUserById(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteById(@PathVariable UUID id){
        adminService.deleteEmployee(id);
    }

    @GetMapping("name")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getByName(@RequestParam("name") String name) {
        return adminService.getUsersByName(name)
                .stream()
                .map(UserMapper::map)
                .toList();
    }
}

