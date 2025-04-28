package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.entity.Visit;
import org.example.entity.VisitId;

import java.util.List;
import java.util.Optional;

public class VisitDao {
    private final EntityManager entityManager;

    public VisitDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Visit visit) {
        executeInsideTransaction(entityManager -> entityManager.persist(visit));
    }

    public Optional<Visit> findById(VisitId visitId) {
        return Optional.ofNullable(entityManager.find(Visit.class, visitId));
    }

    public List<Visit> findAll() {
        return entityManager.createQuery("SELECT v FROM Visit v", Visit.class).getResultList();
    }

    public void update(Visit visit) {
        executeInsideTransaction(entityManager -> entityManager.merge(visit));
    }

    public void delete(Visit visit) {
        executeInsideTransaction(entityManager -> {
            if (entityManager.contains(visit)) {
                entityManager.remove(visit);
            } else {
                entityManager.remove(entityManager.merge(visit));
            }
        });
    }

    public void deleteById(VisitId visitId) {
        findById(visitId).ifPresent(this::delete);
    }

    private void executeInsideTransaction(EntityManagerConsumer action) {
        EntityTransaction transaction = entityManager.getTransaction();

        boolean isNewTransaction = !transaction.isActive(); // Проверяем, есть ли уже транзакция
        try {
            if (isNewTransaction) {
                transaction.begin();
            }

            action.accept(entityManager);

            if (isNewTransaction) {
                transaction.commit();
            }
        } catch (Exception e) {
            if (isNewTransaction && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Transaction failed", e);
        }
    }


    @FunctionalInterface
    private interface EntityManagerConsumer {
        void accept(EntityManager entityManager);
    }
}
