package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ConfirmationTokenExpired;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Security.config.AppRoles;
import com.biblioteka.Library.Token.ConfirmationToken;
import com.biblioteka.Library.dto.Mapper.UserMapper;
import com.biblioteka.Library.dto.RegistrationRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    UserService userService;
    ConfirmationTokenService confirmationTokenService;

    @Autowired
    public RegistrationService(UserService userService, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
    }

    private void possibleToRegister(String username){
        if(userService.userExistsByUsername(username)) throw new EmailAlreadyExistsException();
    }

    public void register(RegistrationRequest registrationRequest) {
        possibleToRegister(registrationRequest.getUsername());                      //if not possible, throws exception
        User user = UserMapper.map(registrationRequest);
        user.setRole(AppRoles.USER);
        userService.createUser(user);
    }
    public void registerEmployeeOrAdmin(RegistrationRequest registrationRequest, String role) {
        possibleToRegister(registrationRequest.getUsername());                      //if not possible, throws exception
        User user = UserMapper.map(registrationRequest);
        user.setRole(AppRoles.valueOf(role));
        userService.createUser(user);
    }

    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if(confirmationToken.getExpiredAt().isBefore(LocalDateTime.now()))
            throw new ConfirmationTokenExpired();

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        userService.enableUser(confirmationToken.getUser().getUsername());
    }
}
