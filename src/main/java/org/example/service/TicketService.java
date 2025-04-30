package org.example.service;

import org.example.entity.Ticket;
import org.example.entity.User;
import org.example.repository.TicketRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository; // 🔧 добавлено поле

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository; // 🔧 присвоено поле
    }

    public void saveTicket(Ticket ticket) {
        if (ticket.getValidFrom().isAfter(ticket.getValidTo())) {
            throw new IllegalArgumentException("Valid from date must be before valid to date");
        }

        Long userId = ticket.getUser().getId();
        User userFromDb = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id " + userId));

        ticket.setUser(userFromDb);

        ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket == null) {
            return null;
        }
        existingTicket.setTicketType(ticket.getTicketType());
        existingTicket.setPrice(ticket.getPrice());
        existingTicket.setValidFrom(ticket.getValidFrom());
        existingTicket.setValidTo(ticket.getValidTo());
        ticketRepository.save(existingTicket);
        return existingTicket;
    }

    public boolean deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public List<Ticket> getTicketsByValidityPeriod(String start, String end) {
        // Преобразование строк в LocalDate, фильтрация по дате
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ticketRepository.findByValidFromBetween(startDate, endDate);
    }

    public Ticket partialUpdateTicket(Long id, Ticket ticket) {
        Ticket existingTicket = ticketRepository.findById(id).orElse(null);
        if (existingTicket == null) {
            return null;
        }
        if (ticket.getTicketType() != null) {
            existingTicket.setTicketType(ticket.getTicketType());
        }
        if (ticket.getPrice() != null) {
            existingTicket.setPrice(ticket.getPrice());
        }
        if (ticket.getValidFrom() != null) {
            existingTicket.setValidFrom(ticket.getValidFrom());
        }
        if (ticket.getValidTo() != null) {
            existingTicket.setValidTo(ticket.getValidTo());
        }
        ticketRepository.save(existingTicket);
        return existingTicket;
    }
}
