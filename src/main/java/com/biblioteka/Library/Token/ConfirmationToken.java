package com.biblioteka.Library.Token;

import com.biblioteka.Library.Entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    private String token;
    @Column(name = "created_at",
            nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "expires_at",
            nullable = false)
    private LocalDateTime expiredAt;
    @Column(name = "confirmation_status")
    private boolean confirmed = false;
    @Enumerated(EnumType.STRING)
    private TokenCategory category;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             User user,
                             TokenCategory category) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user = user;
        this.category = category;
    }
}
