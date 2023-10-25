package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Registration.AppRoles;
import com.biblioteka.Library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("not Found"));
    }

    public void createUser(User user) {
        user.setAppUserRole(AppRoles.USER);
        userRepository.save(user);
    }
}
