package com.quedav1.quedav1back.transaction.adapter.out.persistence.user;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String timezone;

    private Instant createdAt;

    private Instant updatedAt;
}
