package org.example.controller;

import org.example.entity.Visit;
import org.example.entity.VisitId;
import org.example.service.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        Visit savedVisit = visitService.save(visit);
        return ResponseEntity.ok(savedVisit);
    }

    @GetMapping("/{userId}/{attractionId}")
    public ResponseEntity<Visit> getVisit(
            @PathVariable Long userId,
            @PathVariable Long attractionId,
            @RequestParam String visitTime) {
        VisitId id = new VisitId(userId, attractionId, java.time.LocalDateTime.parse(visitTime));
        return visitService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return ResponseEntity.ok(visitService.findAll());
    }

    @PutMapping
    public ResponseEntity<Visit> updateVisit(@RequestBody Visit visit) {
        return ResponseEntity.ok(visitService.update(visit));
    }

    @DeleteMapping("/{userId}/{attractionId}")
    public ResponseEntity<Void> deleteVisit(
            @PathVariable Long userId,
            @PathVariable Long attractionId,
            @RequestParam String visitTime) {
        VisitId id = new VisitId(userId, attractionId, java.time.LocalDateTime.parse(visitTime));
        visitService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
