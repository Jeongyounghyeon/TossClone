package com.tossclone.app.domain;

import com.tossclone.app.dto.UserDTO;
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

@Entity(name = "`user`")
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper = true)
public class User extends AuditingFields {

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

    private User(String userId,
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

    public static User of(
            String userId,
            String userPassword,
            String name,
            String englishName,
            LocalDate dob,
            String phoneNumber,
            String email
    ) {
        return new User(
                userId,
                userPassword,
                name,
                englishName,
                dob,
                phoneNumber,
                email
        );
    }

    public static User from(UserDTO userDTO) {
        return User.of(
                userDTO.userId(),
                userDTO.userPassword(),
                userDTO.name(),
                userDTO.englishName(),
                userDTO.dob(),
                userDTO.phoneNumber(),
                userDTO.email()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof User that)) { return false; }
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
