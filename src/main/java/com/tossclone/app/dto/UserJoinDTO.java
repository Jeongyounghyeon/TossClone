package com.tossclone.app.dto;

import com.tossclone.app.domain.User;
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
        return new UserJoinDTO(
                user.getId(),
                user.getPassword(),
                user.getName(),
                user.getEnglishName(),
                user.getDob(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }
}
