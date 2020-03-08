package codetally.repository;

import codetally.model.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByOwnerName(String owner, String name);
    List<Project> findByProjectnameContainingIgnoreCase(String keywords, Pageable pageable);
}
