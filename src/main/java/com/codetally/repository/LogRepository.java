package com.codetally.repository;

import com.codetally.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
