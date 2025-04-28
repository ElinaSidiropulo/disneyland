package org.example.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Embeddable;

@Embeddable
public class VisitId implements Serializable {
    private Long userId;
    private Long attractionId;
    private LocalDateTime visitTime;

    public VisitId() {}

    public VisitId(Long userId, Long attractionId, LocalDateTime visitTime) {
        this.userId = userId;
        this.attractionId = attractionId;
        this.visitTime = visitTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitId visitId = (VisitId) o;
        return Objects.equals(userId, visitId.userId) &&
                Objects.equals(attractionId, visitId.attractionId) &&
                Objects.equals(visitTime, visitId.visitTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, attractionId, visitTime);
    }
}
