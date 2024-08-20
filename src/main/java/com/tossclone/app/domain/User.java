package com.tossclone.app.domain;

import com.tossclone.app.dto.UserJoinDTO;
import com.tossclone.app.dto.UserUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(length = 255)
    @Setter
    private String id;

    @Column(nullable = false,
            length = 255)
    @Setter
    private String password;

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

    private User(String id,
                 String password,
                 String name,
                 String englishName,
                 LocalDate dob,
                 String phoneNumber,
                 String email) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.englishName = englishName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void update(UserUpdateDTO userUpdateDTO) {
        this.password = userUpdateDTO.password();
        this.name = userUpdateDTO.name();
        this.dob = userUpdateDTO.dob();
        this.englishName = userUpdateDTO.englishName();
        this.phoneNumber = userUpdateDTO.phoneNumber();
        this.email = userUpdateDTO.email();
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

    public static User from(UserJoinDTO userJoinDTO) {
        return User.of(
                userJoinDTO.id(),
                userJoinDTO.password(),
                userJoinDTO.name(),
                userJoinDTO.englishName(),
                userJoinDTO.dob(),
                userJoinDTO.phoneNumber(),
                userJoinDTO.email()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof User that)) { return false; }
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
