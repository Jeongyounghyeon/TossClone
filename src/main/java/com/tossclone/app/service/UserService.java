package com.tossclone.app.service;

import com.tossclone.app.domain.UserAccount;
import com.tossclone.app.dto.UserAccountDTO;
import com.tossclone.app.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserAccountRepository userAccountRepository;

    public boolean isUserIdDuplicate(String userId) {
        return userAccountRepository.existsByUserId(userId);
    }

    public boolean isEmailDuplicate(String email) {
        return userAccountRepository.existsByEmail(email);
    }

    public UserAccountDTO saveUser(UserAccountDTO userAccountDTO) {
        return UserAccountDTO.from(
                userAccountRepository.save(
                        UserAccount.from(userAccountDTO)
                )
        );
    }
}
