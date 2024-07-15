package com.tossclone.app.dto;

import com.tossclone.app.domain.User;
import java.io.Serializable;
import java.time.LocalDate;

public record UserDTO(
        String userId,
        String userPassword,
        String name,
        String englishName,
        LocalDate dob,
        String phoneNumber,
        String email
) implements
        Serializable {

    public static UserDTO from(User user) {
        return new UserDTO(
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
