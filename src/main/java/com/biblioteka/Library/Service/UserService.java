package com.biblioteka.Library.Service;

import com.biblioteka.Library.DTO.Mapper.UserMapper;
import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Security.config.AppRoles;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not Found"));
    }
    public User createUser(RegistrationRequest request, AppRoles role) {
        if(userRepository.existsByUsername(request.getUsername())) throw new EmailAlreadyExistsException();
        var user = UserMapper.map(request);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userRepository.save(user);
        return user;
    }
    public void enableUser(String username){
        userRepository.enableUser(username);
    }
    public void changePassword(String username, String password){
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }
}
