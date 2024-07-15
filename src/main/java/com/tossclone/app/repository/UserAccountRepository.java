package com.tossclone.app.repository;

import com.tossclone.app.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
