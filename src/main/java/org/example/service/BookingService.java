package org.example.service;

import org.example.entity.Booking;
import org.example.repository.AttractionRepository;
import org.example.repository.BookingRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final UserRepository userRepository;
    private final AttractionRepository attractionRepository;
    private final BookingRepository bookingRepository;

    // Обновлённый конструктор для внедрения зависимостей
    @Autowired
    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          AttractionRepository attractionRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.attractionRepository = attractionRepository;
    }

    public Booking saveBooking(Booking booking) {
        // Подгружаем пользователя из базы
        Long userId = booking.getUser().getId();
        Long attractionId = booking.getAttraction().getId();

        booking.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId)));

        booking.setAttraction(attractionRepository.findById(attractionId)
                .orElseThrow(() -> new RuntimeException("Attraction not found with id " + attractionId)));

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByAttractionId(Long attractionId) {
        return bookingRepository.findByAttractionId(attractionId);
    }
}
