package com.biblioteka.Library.Service;

import com.biblioteka.Library.Exceptions.NotFoundException.ConfirmationTokenNotFound;
import com.biblioteka.Library.Repository.ConfirmationTokenRepository;
import com.biblioteka.Library.Token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken getToken(String token){
        return confirmationTokenRepository.findByToken(token).orElseThrow(ConfirmationTokenNotFound::new);
    }
}
