package com.codetally.repository;

import com.codetally.model.Charge;
import com.codetally.model.github.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
    void deleteAllByRepository(Repository repository);
}
