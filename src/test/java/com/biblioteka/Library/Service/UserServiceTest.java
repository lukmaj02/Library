package com.biblioteka.Library.Service;

import com.biblioteka.Library.AppConfig;
import com.biblioteka.Library.DTO.Mapper.UserMapper;
import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Security.config.AppRoles;
import com.biblioteka.Library.Security.config.WebSecurityConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private BCryptPasswordEncoder bCryptPasswordEncoder;
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
                .date(LocalDate.of(2000,1,1))
                .password("passwordez")
                .phoneNumber("+48 118 288 175")
                .build();

        given(userRepository.existsByUsername(user1.getUsername()))
                .willReturn(true);

        //then

        assertThatThrownBy(() -> underTest.createUser(user1,AppRoles.USER))
                .isInstanceOf(EmailAlreadyExistsException.class);
    }

    @Test
    void changePassword() {
    }
}