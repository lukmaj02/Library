package com.biblioteka.Library.Service;

import com.biblioteka.Library.DTO.RegistrationRequest;
import com.biblioteka.Library.Exceptions.ExistingException.EmailAlreadyExistsException;
import com.biblioteka.Library.Model.User;
import com.biblioteka.Library.Repository.UserRepository;
import com.biblioteka.Library.Security.config.AppRoles;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
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
                .username("januszkorwin@gmail.com")
                .build();

        given(userRepository.existsByUsername(user1.getUsername()))
                .willReturn(true);
        //then
        assertThatThrownBy(() -> underTest.createUser(user1,AppRoles.USER))
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
}