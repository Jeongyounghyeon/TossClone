package com.tossclone.app.dto;

import com.tossclone.app.domain.User;
import com.tossclone.app.domain.UserProfile;
import java.io.Serializable;
import java.time.LocalDate;

public record UserUpdateDTO(
        String password,
        String name,
        String englishName,
        LocalDate dob,
        String phoneNumber,
        String email
) implements
        Serializable {

    public static UserUpdateDTO from(User user) {
        UserProfile userProfile = user.getUserProfile();

        return new UserUpdateDTO(
                user.getPassword(),
                userProfile.getName(),
                userProfile.getEnglishName(),
                userProfile.getDob(),
                userProfile.getPhoneNumber(),
                userProfile.getEmail()
        );
    }
}