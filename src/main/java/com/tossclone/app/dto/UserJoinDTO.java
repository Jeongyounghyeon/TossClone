package com.tossclone.app.dto;

import com.tossclone.app.domain.User;
import com.tossclone.app.domain.UserProfile;
import java.io.Serializable;
import java.time.LocalDate;

public record UserJoinDTO(
        String id,
        String password,
        String name,
        String englishName,
        LocalDate dob,
        String phoneNumber,
        String email
) implements
        Serializable {

    public static UserJoinDTO from(User user) {
        UserProfile userProfile = user.getUserProfile();

        return new UserJoinDTO(
                user.getId(),
                user.getPassword(),
                userProfile.getName(),
                userProfile.getEnglishName(),
                userProfile.getDob(),
                userProfile.getPhoneNumber(),
                userProfile.getEmail()
        );
    }
}
