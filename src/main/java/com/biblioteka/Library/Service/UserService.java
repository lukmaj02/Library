package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.Book;
import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Token.ConfirmationToken;
import com.biblioteka.Library.dto.BookResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    //private final String currentlyAuthenticated;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       ConfirmationTokenService confirmationTokenService,
                       BookService bookService,
                       ModelMapper modelMapper) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.confirmationTokenService = confirmationTokenService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
        //this.currentlyAuthenticated = currentlyAuthenticated;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not Found"));
    }
    @Transactional
    public void createUser(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {throw new EmailAlreadyExistsException();}

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
    }

    public void enableUser(String username){
        userRepository.enableUser(username);
    }

    public Collection<BookResponse> getUserBooks(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not Found"));
        return Arrays.asList(modelMapper.map(bookService.getUserBooks(user), BookResponse[].class));
    }
    @Transactional
    public void borrowBook(Integer id, String username){
        Book book = bookService.borrowBookById(id);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not Found"));
        user.getUsers_books().add(book);
    }

    public void getUserBookWithId(Integer id) {

    }
}
