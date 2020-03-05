package com.codetally.repository;

import com.codetally.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByOwnerName(String owner, String name);
}
