package codetally.repository;

import codetally.model.Charge;
import codetally.model.github.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
    void deleteAllByRepository(Repository repository);
}
