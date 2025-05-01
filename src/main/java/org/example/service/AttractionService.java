package org.example.service;

import org.example.entity.Attraction;
import org.example.repository.AttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttractionService {

    private final AttractionRepository attractionRepository;

    public AttractionService(AttractionRepository attractionRepository) {
        this.attractionRepository = attractionRepository;
    }

    public Attraction saveAttraction(Attraction attraction) {
        return attractionRepository.save(attraction);
    }

    public List<Attraction> getAllAttractions() {
        return attractionRepository.findAll();
    }

    public Optional<Attraction> getAttractionById(Long id) {
        return attractionRepository.findById(id);
    }

    public Attraction updateAttraction(Long id, Attraction updatedAttraction) {
        return attractionRepository.findById(id).map(attraction -> {
            attraction.setName(updatedAttraction.getName());
            attraction.setDescription(updatedAttraction.getDescription());
            attraction.setMinHeight(updatedAttraction.getMinHeight());
            attraction.setMaxCapacity(updatedAttraction.getMaxCapacity());
            attraction.setDuration(updatedAttraction.getDuration());
            return attractionRepository.save(attraction);
        }).orElse(null);
    }

    public boolean deleteAttraction(Long id) {
        if (attractionRepository.existsById(id)) {
            attractionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
