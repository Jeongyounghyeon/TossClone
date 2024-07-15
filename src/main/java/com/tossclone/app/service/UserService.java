package com.tossclone.app.service;

import com.tossclone.app.domain.User;
import com.tossclone.app.dto.UserDTO;
import com.tossclone.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final UserRepository userRepository;

    public boolean isUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        return UserDTO.from(
                userRepository.save(
                        User.from(userDTO)
                )
        );
    }
}
