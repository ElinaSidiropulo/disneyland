package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @EmbeddedId
    private VisitId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("attractionId")
    @JoinColumn(name = "attraction_id", nullable = false)
    private Attraction attraction;

    @Column(name = "visit_time", nullable = false)
    private LocalDateTime visitTime;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Visit() {}

    // Новый конструктор с VisitId
    public Visit(VisitId id, User user, Attraction attraction) {
        this.id = id;
        this.user = user;
        this.attraction = attraction;
        this.visitTime = id.getVisitTime();
    }

    // Оставляем существующий конструктор
    public Visit(User user, Attraction attraction, LocalDateTime visitTime, Ticket ticket) {
        this.id = new VisitId(user.getId(), attraction.getId(), visitTime);
        this.user = user;
        this.attraction = attraction;
        this.visitTime = visitTime;
        this.ticket = ticket;
    }

    public VisitId getId() {
        return id;
    }

    public void setId(VisitId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attraction getAttraction() {
        return attraction;
    }

    public void setAttraction(Attraction attraction) {
        this.attraction = attraction;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", user=" + user +
                ", attraction=" + attraction +
                ", visitTime=" + visitTime +
                ", ticket=" + ticket +
                '}';
    }
}
