package com.biblioteka.Library.dto.Mapper;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.dto.RegistrationRequest;
import com.biblioteka.Library.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class UserMapper {

    public static UserDto map(User user){
        return UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User map (RegistrationRequest registrationRequest){
        return User.builder()
                .name(registrationRequest.getName())
                .surname(registrationRequest.getSurname())
                .date(registrationRequest.getDate())
                .username(registrationRequest.getUsername())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .password(registrationRequest.getPassword())
                .build();
    }
}
