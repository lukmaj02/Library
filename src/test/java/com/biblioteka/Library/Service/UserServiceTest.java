package com.biblioteka.Library.Service;

import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Security.config.AppRoles;
<<<<<<< HEAD
=======
import jakarta.inject.Inject;
>>>>>>> 0ca9597ac6600f105fe873eb78b949e5c950705d
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UsernameNotFoundException;
=======
>>>>>>> 0ca9597ac6600f105fe873eb78b949e5c950705d
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(userRepository, bCryptPasswordEncoder);
    }

    @Test
    void shouldNotCreateUserWhenEmailAlreadyExists() {
        //given
        var user1 = RegistrationRequest.builder()
                .username("januszkorwin@gmail.com")
<<<<<<< HEAD
                .date(LocalDate.of(2000, 1, 1))
                .password("passwordez")
                .phoneNumber("+48 118 288 175")
=======
>>>>>>> 0ca9597ac6600f105fe873eb78b949e5c950705d
                .build();
        given(userRepository.existsByUsername(user1.getUsername()))
                .willReturn(true);
        //then
<<<<<<< HEAD
        assertThatThrownBy(() -> underTest.createUser(user1, AppRoles.USER))
=======
        assertThatThrownBy(() -> underTest.createUser(user1,AppRoles.USER))
>>>>>>> 0ca9597ac6600f105fe873eb78b949e5c950705d
                .isInstanceOf(EmailAlreadyExistsException.class);
    }

    @Test
    void changePassword() {
        //given
        var user = new User();
        user.setUsername("janusz@gmail.com");
        //given(userRepository.findByUsername("janusz@gmail.com")).willReturn(Optional.of(user));

        //when
        when(userRepository.findByUsername("janusz@gmail.com")).thenReturn(Optional.of(user));
        underTest.changePassword("janusz@gmail.com", "newpassword");
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn();
        //then

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository.save(captor.capture()));
        var capturedUser = captor.getValue();
        assertThat(capturedUser.getPassword()).isEqualTo("newpassword");
    }

    @Test
    void shouldCreateUserWhenEmailIsValid() {
        //given
        var user1 = RegistrationRequest.builder()
                .name("Janusz")
                .surname("Korwin")
                .username("januszkorwin@gmail.com")
                .date(LocalDate.of(2000, 1, 1))
                .password("passwordez")
                .phoneNumber("+48 118 288 175")
                .build();

        given(userRepository.existsByUsername(user1.getUsername()))
                .willReturn(false);
        given(bCryptPasswordEncoder.encode(user1.getPassword())).willReturn(user1.getPassword());

        //when
        underTest.createUser(user1, AppRoles.USER);

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        var user = userArgumentCaptor.getValue();

        assertThat(user.getUsername()).isEqualTo(user1.getUsername());
        assertThat(user.getPassword()).isEqualTo(user1.getPassword());
        assertThat(user.getName()).isEqualTo(user1.getName());
        assertThat(user.getDate()).isEqualTo(user1.getDate());
        assertThat(user.getPhoneNumber()).isEqualTo(user1.getPhoneNumber());
    }

    @Test
    void shouldNotLoadUserDetailsByUsername() {
        //given
        String username = "januszkorwin@gmail.com";
        given(userRepository.findByUsername(username)).willReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> underTest.loadUserByUsername(username)).isInstanceOf(UsernameNotFoundException.class);
    }
}