package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ConfirmationTokenExpired;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Token.ConfirmationToken;
import com.biblioteka.Library.dto.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    UserService userService;
    ModelMapper modelMapper;
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    public RegistrationService(UserService userService, ModelMapper modelMapper, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.confirmationTokenService = confirmationTokenService;
    }

    public User register(RegistrationRequest registrationRequest) {
        User user = modelMapper.map(registrationRequest, User.class);
        userService.createUser(user);
        return user;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new EmailAlreadyExistsException();
        }

        if(confirmationToken.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new ConfirmationTokenExpired();
        }
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        userService.enableUser(confirmationToken.getUser().getUsername());
        return "confirmed";
    }
}
