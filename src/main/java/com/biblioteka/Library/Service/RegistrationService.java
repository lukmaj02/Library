package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ConfirmationTokenExpired;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Exceptions.InvalidPasswordException;
import com.biblioteka.Library.Security.config.AppRoles;
import com.biblioteka.Library.Token.ConfirmationToken;
import com.biblioteka.Library.Token.TokenCategory;
import com.biblioteka.Library.DTO.Mapper.UserMapper;
import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.DTO.ResetPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final static String CONFIRMATION_PATH = "http://localhost:8080/confirm?token=";

    @Autowired
    public RegistrationService(UserService userService, ConfirmationTokenService confirmationTokenService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSenderService = emailSenderService;
    }

    public String register(RegistrationRequest registrationRequest, AppRoles role) {
        if(userService.userExistsByUsername(registrationRequest.getUsername())) throw new EmailAlreadyExistsException();
        var user = UserMapper.map(registrationRequest);
        user.setRole(role);
        userService.createUser(user);
        var token = confirmationTokenService.generateTokenForUser(user,TokenCategory.REGISTRATION);
        emailSenderService.sendMail(TokenCategory.REGISTRATION.toString(), CONFIRMATION_PATH + token.getToken(), user.getUsername());
        return "REGISTERED!";
    }


    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
        if(confirmationToken.getExpiredAt().isBefore(LocalDateTime.now())) throw new ConfirmationTokenExpired();
        confirmationTokenService.saveConfirmation(confirmationToken);

        if(confirmationToken.getCategory().equals(TokenCategory.REGISTRATION))
            userService.enableUser(confirmationToken.getUser().getUsername());
        return "CONFIRMED!";
    }

    public void generateTokenForUserForgotPassword(String username) {
        var user = (User) userService.loadUserByUsername(username);
        var token = confirmationTokenService.generateTokenForUser(user, TokenCategory.PASSWORD_RESET);
        emailSenderService.sendMail(TokenCategory.PASSWORD_RESET.toString(),CONFIRMATION_PATH + token.getToken(), username);
    }

    public String changePassword(String token, ResetPasswordDto password, String username){
        if(!password.getPassword1().equals(password.getPassword2())) throw new InvalidPasswordException();
        if(!confirmationTokenService.getToken(token).isConfirmed()) throw new ConfirmationTokenExpired();
        userService.changePassword(username, password.getPassword1());
        return "Password change successfully";
    }
}
