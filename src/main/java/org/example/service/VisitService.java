package org.example.service;

import org.example.entity.Visit;
import org.example.entity.VisitId;
import org.example.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    public Optional<Visit> findById(VisitId id) {
        return visitRepository.findById(id);
    }

    public List<Visit> findAll() {
        return visitRepository.findAll();
    }

    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }

    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    public void deleteById(VisitId id) {
        visitRepository.deleteById(id);
    }
}
