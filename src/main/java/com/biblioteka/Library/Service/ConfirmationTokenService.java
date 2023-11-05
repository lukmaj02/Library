package com.biblioteka.Library.Service;

import com.biblioteka.Library.Entity.User;
import com.biblioteka.Library.Exceptions.NotFoundException.ConfirmationTokenNotFound;
import com.biblioteka.Library.Repository.ConfirmationTokenRepository;
import com.biblioteka.Library.Token.ConfirmationToken;
import com.biblioteka.Library.Token.TokenCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ConfirmationToken getToken(String token){
        return confirmationTokenRepository.findByToken(token).orElseThrow(ConfirmationTokenNotFound::new);
    }

    public void generateTokenForUser(User user, TokenCategory tokenCategory){
        ConfirmationToken confirmationToken = new ConfirmationToken(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                user,
                tokenCategory
        );
        confirmationTokenRepository.save(confirmationToken);
    }

    public void saveConfirmation(ConfirmationToken confirmationToken){
        confirmationToken.setConfirmed(true);
        confirmationTokenRepository.save(confirmationToken);
    }
}
