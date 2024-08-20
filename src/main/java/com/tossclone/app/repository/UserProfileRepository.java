package com.tossclone.app.repository;

import com.tossclone.app.domain.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    boolean existsByEmail(String email);
}