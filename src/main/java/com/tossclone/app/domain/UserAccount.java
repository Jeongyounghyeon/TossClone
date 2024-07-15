package com.tossclone.app.domain;

import com.tossclone.app.dto.UserAccountDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
public class UserAccount extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    @Setter
    private String userId;

    @Column(nullable = false,
            length = 255)
    @Setter
    private String userPassword;

    @Column(nullable = false,
            length = 255)
    @Setter
    private String name;

    @Column(nullable = false,
            length = 255)
    @Setter
    private String englishName;

    @Column(nullable = false)
    @Setter
    private LocalDate dob;

    @Column(nullable = false,
            length = 15)
    @Setter
    private String phoneNumber;

    @Column(nullable = false,
            length = 255)
    @Setter
    private String email;

    private UserAccount(String userId,
                       String userPassword,
                       String name,
                       String englishName,
                       LocalDate dob,
                       String phoneNumber,
                       String email) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.name = name;
        this.englishName = englishName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static UserAccount of(
            String userId,
            String userPassword,
            String name,
            String englishName,
            LocalDate dob,
            String phoneNumber,
            String email
    ) {
        return new UserAccount(
                userId,
                userPassword,
                name,
                englishName,
                dob,
                phoneNumber,
                email
        );
    }

    public static UserAccount from(UserAccountDTO userAccountDTO) {
        return UserAccount.of(
                userAccountDTO.userId(),
                userAccountDTO.userPassword(),
                userAccountDTO.name(),
                userAccountDTO.englishName(),
                userAccountDTO.dob(),
                userAccountDTO.phoneNumber(),
                userAccountDTO.email()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof UserAccount that)) { return false; }
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
