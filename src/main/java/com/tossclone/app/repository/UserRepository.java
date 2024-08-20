package com.tossclone.app.repository;

import com.tossclone.app.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(String id);
    Optional<User> findById(String userId);
}
