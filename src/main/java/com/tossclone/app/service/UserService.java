package com.tossclone.app.service;

import com.tossclone.app.domain.User;
import com.tossclone.app.dto.UserJoinDTO;
import com.tossclone.app.dto.UserUpdateDTO;
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

    public UserJoinDTO saveUser(UserJoinDTO userJoinDTO) {
        return UserJoinDTO.from(
                userRepository.save(
                        User.from(userJoinDTO)
                )
        );
    }

    public Optional<UserJoinDTO> findUserByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .flatMap(user -> Optional.ofNullable(UserJoinDTO.from(user)));
    }

    public UserUpdateDTO updateUser(String userId, UserUpdateDTO userUpdateDTO) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.update(userUpdateDTO);

            return UserUpdateDTO.from(
                    userRepository.save(user)
            );
        } else {
            throw new EntityNotFoundException(
                    String.format("user(%s) does not exist in the repository", userId)
            );
        }
    }
}
