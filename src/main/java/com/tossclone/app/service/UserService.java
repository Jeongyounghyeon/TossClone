package com.tossclone.app.service;

import com.tossclone.app.domain.User;
import com.tossclone.app.dto.UserDTO;
import com.tossclone.app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.Optional;
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

    public Optional<UserDTO> findUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .flatMap(user -> Optional.ofNullable(UserDTO.from(user)));
    }

    public UserDTO updateUser(String userId, UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setUserPassword(userDTO.userPassword());
            user.setName(userDTO.name());
            user.setDob(userDTO.dob());
            user.setEnglishName(userDTO.englishName());
            user.setPhoneNumber(userDTO.phoneNumber());
            user.setEmail(userDTO.email());

            return UserDTO.from(
                    userRepository.save(user)
            );
        } else {
            throw new EntityNotFoundException(
                    String.format("user(%s) does not exist in the repository", userId)
            );
        }
    }
}
