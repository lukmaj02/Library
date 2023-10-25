package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Registration.AppRoles;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.dto.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    UserService userService;
    ModelMapper modelMapper;
    @Autowired
    public RegistrationService(UserService userService, ModelMapper modelMapper) {
        this.userService= userService;
        this.modelMapper = modelMapper;
    }

    public User register(RegistrationRequest registrationRequest){
        User user = modelMapper.map(registrationRequest, User.class);
        userService.createUser(user);
        return user;
    }
}
