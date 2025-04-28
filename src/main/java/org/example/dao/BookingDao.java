package org.example.dao;

import org.example.entity.Booking;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BookingDao {
    private static final Logger logger = LoggerFactory.getLogger(BookingDao.class);

    public void saveBooking(Booking booking) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(booking);
            transaction.commit();
            logger.info("Бронирование сохранено: {}", booking);
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            logger.error("Ошибка при сохранении бронирования: ", e);
            throw new RuntimeException("Ошибка при сохранении бронирования", e);
        }
    }

    public List<Booking> getAllBookings() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Booking> query = session.createQuery("FROM Booking", Booking.class);
            List<Booking> bookings = query.list();
            logger.info("Получено бронирований: {}", bookings.size());
            return bookings;
        } catch (Exception e) {
            logger.error("Ошибка при получении списка бронирований: ", e);
            throw new RuntimeException("Ошибка при получении списка бронирований", e);
        }
    }

    public Booking getBookingById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Booking.class, id);
        } catch (Exception e) {
            logger.error("Ошибка при получении бронирования: ", e);
            return null;
        }
    }
}
