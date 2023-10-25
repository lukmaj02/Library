package com.biblioteka.Library.Controller;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Service.RegistrationService;
import com.biblioteka.Library.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }
}
