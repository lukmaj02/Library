package com.biblioteka.Library.Service;


import com.biblioteka.Library.Model.User;

import com.biblioteka.Library.Exceptions.NotFoundException.UserNotFoundException;
import com.biblioteka.Library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final UserRepository userRepository;

    @Autowired
    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    public void deleteEmployee(UUID id) {
        var employee = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.delete(employee);
    }
    public List<User> getUsersByName(String name){
        return userRepository.findByName(name);
    }
}
