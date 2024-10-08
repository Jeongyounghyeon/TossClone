package com.tossclone.app.dto;

import com.tossclone.app.domain.User;
import java.io.Serializable;
import java.time.LocalDate;

public record UserUpdateDTO(
        String userPassword,
        String name,
        String englishName,
        LocalDate dob,
        String phoneNumber,
        String email
) implements
        Serializable {

    public static UserUpdateDTO from(User user) {
        return new UserUpdateDTO(
                user.getUserPassword(),
                user.getName(),
                user.getEnglishName(),
                user.getDob(),
                user.getPhoneNumber(),
                user.getEmail()
        );
    }
}