package com.biblioteka.Library.Service;

import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Security.config.AppRoles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
                .name("Janusz")
                .surname("Korwin")
                .username("januszkorwin@gmail.com")
                .date(LocalDate.of(2000, 1, 1))
                .password("passwordez")
                .phoneNumber("+48 118 288 175")
                .build();
        given(userRepository.existsByUsername(user1.getUsername()))
                .willReturn(true);
        //then
        assertThatThrownBy(() -> underTest.createUser(user1, AppRoles.USER))
                .isInstanceOf(EmailAlreadyExistsException.class);
    }

    @Test
    void changePassword() {
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