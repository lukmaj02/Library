package com.biblioteka.Library.Repository;

import com.biblioteka.Library.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void enableUser() {
        //given
        User user = new User();
        user.setUsername("janusz");
        user.setPassword("123");
        user.setName("Janusz");
        user.setSurname("Korwin");
        //when
        userRepository.enableUser(user.getUsername());
        //then
        assertThat(user.isEnabled()).isEqualTo(true);
    }
}