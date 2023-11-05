package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ConfirmationTokenExpired;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Token.ConfirmationToken;
import com.biblioteka.Library.Token.TokenCategory;
import com.biblioteka.Library.dto.BookDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       ConfirmationTokenService confirmationTokenService,
                       BookService bookService,
                       ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not Found"));
    }

    public boolean userExistsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        confirmationTokenService.generateTokenForUser(user, TokenCategory.CREATION);
    }

    public void enableUser(String username){
        userRepository.enableUser(username);
    }

    public void confirmChangingPassword(String username){
        User user = (User) loadUserByUsername(username);
        confirmationTokenService.generateTokenForUser(user, TokenCategory.PASSWORD);
    }

    public void changePassword(String username, String password){
        var user = (User) loadUserByUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

}
