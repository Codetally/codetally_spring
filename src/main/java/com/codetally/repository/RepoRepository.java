package com.codetally.repository;

import com.codetally.model.User;
import com.codetally.model.github.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepoRepository extends JpaRepository<Repository, Long> {
    Repository findByOwnerName(String owner, String name);
}
