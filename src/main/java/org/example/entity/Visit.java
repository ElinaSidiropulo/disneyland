package org.example.entity;

import jakarta.persistence.*;
import org.example.entity.Attraction;
import org.example.entity.Ticket;
import org.example.entity.User;
import org.example.entity.VisitId;

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

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public Visit() {}

    // Новый конструктор с VisitId
    public Visit(VisitId id, User user, Attraction attraction) {
        this.id = id;
        this.user = user;
        this.attraction = attraction;
    }

    // Оставляем существующий конструктор
    public Visit(User user, Attraction attraction, LocalDateTime visitTime, Ticket ticket) {
        this.id = new VisitId(user.getId(), attraction.getId(), visitTime);
        this.user = user;
        this.attraction = attraction;
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

    // Убираем прямое поле visitTime, а вместо него делаем геттер
    public LocalDateTime getVisitTime() {
        return id != null ? id.getVisitTime() : null;
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
                ", visitTime=" + getVisitTime() +
                ", ticket=" + ticket +
                '}';
    }
}
