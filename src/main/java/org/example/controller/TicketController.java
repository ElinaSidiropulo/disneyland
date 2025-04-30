package org.example.controller;

import jakarta.validation.Valid;
import org.example.entity.Ticket;
import org.example.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@Valid @RequestBody Ticket ticket) {
        ticketService.saveTicket(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @Valid @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.updateTicket(id, ticket);
        return updatedTicket != null ? ResponseEntity.ok(updatedTicket) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        boolean deleted = ticketService.deleteTicket(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Long userId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
        return tickets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tickets);
    }

    @GetMapping("/validity")
    public ResponseEntity<List<Ticket>> getTicketsByValidityPeriod(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {

        List<Ticket> tickets = ticketService.getTicketsByValidityPeriod(start, end);

        return tickets.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(tickets);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> partialUpdateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket updatedTicket = ticketService.partialUpdateTicket(id, ticket);
        return updatedTicket != null ? ResponseEntity.ok(updatedTicket) : ResponseEntity.notFound().build();
    }
}
