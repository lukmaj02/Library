package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.NotFoundException.ConfirmationTokenNotFound;
import com.biblioteka.Library.Repository.ConfirmationTokenRepository;
import com.biblioteka.Library.Token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationToken getToken(String token){
        return confirmationTokenRepository.findByToken(token).orElseThrow(ConfirmationTokenNotFound::new);
    }

    public void generateTokenForUser(User user){
        ConfirmationToken confirmationToken = new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenRepository.save(confirmationToken);
    }
}
