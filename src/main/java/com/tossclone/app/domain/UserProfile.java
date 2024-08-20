package com.tossclone.app.domain;

import static lombok.AccessLevel.PROTECTED;

import com.tossclone.app.dto.UserUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_profile")
@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
public class UserProfile extends AuditingFields {

    @Id
    @Column(length = 255)
    private String id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String englishName;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false, length = 15)
    private String phoneNumber;

    @Column(nullable = false, length = 255)
    private String email;

    @OneToOne(mappedBy = "userProfile")
    private User user;

    private UserProfile(String id, String name, String englishName, LocalDate dob, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.englishName = englishName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static UserProfile of(
            String id,
            String name,
            String englishName,
            LocalDate dob,
            String phoneNumber,
            String email
    ) {
        return new UserProfile(
                id,
                name,
                englishName,
                dob,
                phoneNumber,
                email
        );
    }

    public void update(UserUpdateDTO userUpdateDTO) {
        this.name = userUpdateDTO.name();
        this.dob = userUpdateDTO.dob();
        this.englishName = userUpdateDTO.englishName();
        this.phoneNumber = userUpdateDTO.phoneNumber();
        this.email = userUpdateDTO.email();
    }
}
