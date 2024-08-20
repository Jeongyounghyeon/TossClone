package com.tossclone.app.domain;

import static lombok.AccessLevel.*;

import com.tossclone.app.dto.UserJoinDTO;
import com.tossclone.app.dto.UserUpdateDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "`user`")
@Table(indexes = {
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString(callSuper = true)
public class User extends AuditingFields {

    @Id
    @Column(length = 255)
    private String id;

    @Column(nullable = false,
            length = 255)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private UserProfile userProfile;

    private User(
            String id,
            String password,
            UserProfile userProfile
     ) {
        this.id = id;
        this.password = password;
        this.userProfile = userProfile;
    }

    public void update(UserUpdateDTO userUpdateDTO) {
        this.password = userUpdateDTO.password();

        this.userProfile.update(userUpdateDTO);
    }

    public static User of(
            String id,
            String password,
            String name,
            String englishName,
            LocalDate dob,
            String phoneNumber,
            String email
    ) {
        UserProfile userProfile = UserProfile.of(id, name, englishName, dob, phoneNumber, email);

        return new User(id, password, userProfile);
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
