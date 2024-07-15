package com.tossclone.app.dto;

import com.tossclone.app.domain.UserAccount;
import java.io.Serializable;
import java.time.LocalDate;

public record UserAccountDTO(
        String userId,
        String userPassword,
        String name,
        String englishName,
        LocalDate dob,
        String phoneNumber,
        String email
) implements
        Serializable {

    public static UserAccountDTO from(UserAccount userAccount) {
        return new UserAccountDTO(
                userAccount.getUserId(),
                userAccount.getUserPassword(),
                userAccount.getName(),
                userAccount.getEnglishName(),
                userAccount.getDob(),
                userAccount.getPhoneNumber(),
                userAccount.getEmail()
        );
    }
}
