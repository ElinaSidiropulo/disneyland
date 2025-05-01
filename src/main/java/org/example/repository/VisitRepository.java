package org.example.repository;

import org.example.entity.Visit;
import org.example.entity.VisitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<Visit, VisitId> {
    // можно добавлять свои методы, если понадобится
}
