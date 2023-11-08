package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Security.config.AppRoles;
import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.DTO.ResetPasswordDto;
import com.biblioteka.Library.Token.TokenCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("")
public class RegistrationController {

    private final RegistrationService registrationService;
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest, AppRoles.USER);
    }

    @PostMapping("/registration/admin-mode")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public String registerEmployeeOrAdmin(@RequestBody RegistrationRequest registrationRequest, @RequestParam("role") String role){
        return registrationService.register(registrationRequest, AppRoles.valueOf(role));
    }

    @GetMapping("/confirm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @PutMapping("/password-reset")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestParam("username") String username){
        registrationService.generateTokenForUserForgotPassword(username);
    }

    @GetMapping("/password-set")
    @ResponseStatus(HttpStatus.OK)
    public void acceptChangePassword(@RequestParam("username") String username,
                                     @RequestParam("token") String token){
        registrationService.confirmToken(token);
    }
    @PutMapping("/password-set")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String changePassword(@RequestParam("username") String username,
                               @RequestParam("token") String token,
                               @RequestBody ResetPasswordDto password){
        return registrationService.changePassword(token, password, username);
    }
}
