package org.example.repository;

import org.example.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Метод для поиска билетов по диапазону дат
    List<Ticket> findByValidFromBetween(LocalDate startDate, LocalDate endDate);

    // Метод для поиска билетов по userId (если еще не добавлен)
    List<Ticket> findByUserId(Long userId);
}
