package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.dto.RegistrationRequest;
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
    public void register(@RequestBody RegistrationRequest registrationRequest){
        registrationService.register(registrationRequest);
    }

    @PostMapping("/registration/admin-mode")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public void registerEmployeeOrAdmin(@RequestBody RegistrationRequest registrationRequest, @RequestParam("role") String role){
        registrationService.registerEmployeeOrAdmin(registrationRequest, role);
    }

    @GetMapping("/confirm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void confirm(@RequestParam("token") String token){
        registrationService.confirmToken(token);
    }

    @GetMapping("/forgotPassword")
    @ResponseStatus(HttpStatus.OK)
    public void forgotPassword(@RequestParam("username") String username){
        registrationService.generateTokenForUserForgotPassword(username);
    }
    @PutMapping("/changePassword")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePassword(@RequestParam("username") String username,
                               @RequestParam("token") String token,
                               @RequestBody String password){
        registrationService.changePassword(token, username, password);
    }
}
