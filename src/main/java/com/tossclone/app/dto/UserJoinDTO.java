package com.tossclone.app.dto;

import com.tossclone.app.domain.User;
import java.io.Serializable;
import java.time.LocalDate;

public record UserJoinDTO(
        String userId,
        String userPassword,
        String name,
        String englishName,
        LocalDate dob,
        String phoneNumber,
        String email
) implements
        Serializable {

    public static UserJoinDTO from(User user) {
        return new UserJoinDTO(
                user.getUserId(),
                user.getUserPassword(),
                user.getName(),
                user.getEnglishName(),
                user.getDob(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }
}
