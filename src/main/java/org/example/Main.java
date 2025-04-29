//package org.example;
//
//import org.example.dao.TicketDao;
//import org.example.dao.UserDao;
//import org.example.entity.Ticket;
//import org.example.entity.User;
//import org.example.util.HibernateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//public class Main {
//    public static void main(String[] args) {
//        // Создаем DAO для работы с User
//        UserDao userDao = new UserDao();
//        TicketDao ticketDao = new TicketDao();
//
//        // Получаем существующего пользователя (предполагаем, что есть пользователь с ID 1)
//        User user = userDao.getUserById(1L);
//        if (user == null) {
//            System.out.println("Пользователь с ID 1 не найден! Завершение программы.");
//            HibernateUtil.shutdown();
//            return;
//        }
//
//        // Создаем новый билет
//        Ticket ticket = new Ticket();
//        ticket.setUser(user);
//        ticket.setTicketType("Standard");
//        ticket.setPrice(new BigDecimal("100.00"));
//        ticket.setValidFrom(LocalDate.now());
//        ticket.setValidTo(LocalDate.now().plusDays(30));
//
//        // Сохраняем билет
//        try {
//            ticketDao.saveTicket(ticket);
//            System.out.println("Билет успешно сохранен!");
//        } catch (Exception e) {
//            System.err.println("Ошибка при сохранении билета: " + e.getMessage());
//        }
//
//        // Завершаем работу Hibernate
//        HibernateUtil.shutdown();
//    }
//}
//
//
package org.example;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        try {
            User user = new User();
            user.setName("auffy");
            user.setEmail("auffy@example.com");

            userDao.saveUser(user);
            logger.info("Пользователь успешно сохранен: {}", user);

            userDao.getAllUsers().forEach(u -> logger.info("Найден пользователь: {}", u));
        } catch (Exception e) {
            logger.error("Ошибка в основном методе Main: ", e);
        }
    }
}

//package org.example;
//
//import org.example.dao.AttractionDao;
//import org.example.entity.Attraction;
//import org.example.util.HibernateUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Main {
//    public static void main(String[] args) {
//        Logger logger = LoggerFactory.getLogger(Main.class);
//        AttractionDao attractionDao = new AttractionDao();
//
//        // Создаем новый аттракцион
//        Attraction attraction = new Attraction();
//        attraction.setName("Американские горкиАААА");
//        attraction.setDescription("Захватывающие американские горки с крутыми спусками!");
//        attraction.setMinHeight(120);
//        attraction.setMaxCapacity(20);
//        attraction.setDuration(5);
//
//        // Сохраняем аттракцион
//        try {
//            attractionDao.saveAttraction(attraction);
//            System.out.println("Аттракцион успешно сохранен!");
//        } catch (Exception e) {
//            System.err.println("Ошибка при сохранении аттракциона: " + e.getMessage());
//        }
//
//        // Завершаем работу Hibernate
//        HibernateUtil.shutdown();
//    }
//}

//package org.example;
//
//import org.example.dao.BookingDao;
//import org.example.dao.UserDao;
//import org.example.dao.AttractionDao;
//import org.example.entity.Booking;
//import org.example.entity.User;
//import org.example.entity.Attraction;
//import org.example.entity.Booking.BookingStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//public class Main {
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);
//
//    public static void main(String[] args) {
//        BookingDao bookingDao = new BookingDao();
//        UserDao userDao = new UserDao();
//        AttractionDao attractionDao = new AttractionDao();
//
//        try {
//            // Получаем пользователя и аттракцион из базы (замените ID на существующие)
//            User user = userDao.getUserById(1L);
//            Attraction attraction = attractionDao.getAttractionById(1L);
//
//            if (user == null || attraction == null) {
//                logger.error("Ошибка: Пользователь или аттракцион не найдены!");
//                return;
//            }
//
//            // Создаём бронирование
//            Booking booking = new Booking();
//            booking.setUser(user);
//            booking.setAttraction(attraction);
//            booking.setStatus(BookingStatus.cancelled);
//            booking.setBookingTime(LocalDateTime.now()); // Устанавливаем дату бронирования
//
//// Сохраняем бронирование
//            bookingDao.saveBooking(booking);
//            logger.info("Бронирование успешно сохранено: {}", booking);
//
//            // Выводим все бронирования
//            bookingDao.getAllBookings().forEach(b -> logger.info("Найдено бронирование: {}", b));
//        } catch (Exception e) {
//            logger.error("Ошибка в основном методе Main: ", e);
//        }
//    }
//}

//
//package org.example;
//
//import org.example.dao.ReviewDao;
//import org.example.entity.Review;
//import org.example.entity.User;
//import org.example.entity.Attraction;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class Main {
//    private static final Logger logger = LoggerFactory.getLogger(Main.class);
//
//    public static void main(String[] args) {
//        ReviewDao reviewDao = new ReviewDao();
//
//        try {
//            // Создание объекта пользователя (предполагаем, что он уже существует в базе)
//            User user = new User();
//            user.setId(1L); // Пример ID пользователя, необходимо удостовериться, что такой пользователь существует
//
//            // Создание объекта аттракциона (предполагаем, что аттракцион тоже существует в базе)
//            Attraction attraction = new Attraction();
//            attraction.setId(1L); // Пример ID аттракциона
//
//            // Создание отзыва
//            Review review = new Review();
//            review.setUser(user);
//            review.setAttraction(attraction);
//            review.setRating(5);
//            review.setComment("Блин яЯЯЯя падаю блин");
//
//            // Сохранение отзыва
//            reviewDao.saveReview(review);
//            logger.info("Отзыв успешно сохранен: {}", review);
//
//            // Получение всех отзывов для пользователя
//            reviewDao.getReviewsByUserId(user.getId()).forEach(r -> logger.info("Найден отзыв для пользователя: {}", r));
//
//            // Получение всех отзывов для аттракциона
//            reviewDao.getReviewsByAttractionId(attraction.getId()).forEach(r -> logger.info("Найден отзыв для аттракциона: {}", r));
//
//        } catch (Exception e) {
//            logger.error("Ошибка в основном методе Main: ", e);
//        }
//    }
//}

//package org.example;
//
//import org.example.dao.VisitDao;
//import org.example.entity.Visit;
//import org.example.entity.VisitId;
//import org.example.entity.User;
//import org.example.entity.Attraction;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//public class Main {
//    public static void main(String[] args) {
//        try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//             Session session = sessionFactory.openSession()) {
//
//            VisitDao visitDao = new VisitDao(session);
//            VisitId visitId = new VisitId(1L, 1L, LocalDateTime.now());
//
//            session.beginTransaction();
//            Optional<Visit> existingVisit = visitDao.findById(visitId);
//
//            if (existingVisit.isEmpty()) {
//                User user = session.get(User.class, 1L);
//                Attraction attraction = session.get(Attraction.class, 1L);
//
//                if (user == null || attraction == null) {
//                    System.out.println("User или Attraction с указанными ID не найдены.");
//                } else {
//                    Visit visit = new Visit(visitId, user, attraction);
//                    visitDao.save(visit);
//                    System.out.println("Посещение успешно добавлено: " + visit);
//                }
//            } else {
//                System.out.println("Посещение уже существует: " + existingVisit.get());
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

//package org.example;
//
//import org.example.dao.ScheduleDao;
//import org.example.entity.Attraction;
//import org.example.entity.Schedule;
//import org.example.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import java.time.LocalTime;
//
//public class Main {
//    public static void main(String[] args) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        ScheduleDao scheduleDao = new ScheduleDao();
//
//        // Открываем сессию для загрузки Attraction
//        Attraction attraction;
//        try (Session session = sessionFactory.openSession()) {
//            attraction = session.get(Attraction.class, 1L); // Загружаем Attraction с id = 1
//        }
//
//        if (attraction == null) {
//            System.err.println("Ошибка: Аттракцион с id = 1 не найден.");
//            return;
//        }
//
//        // Создаем и сохраняем расписание
//        Schedule schedule = new Schedule();
//        schedule.setAttraction(attraction); // Устанавливаем аттракцион
//        schedule.setDayOfWeek("Thursday");
//        schedule.setOpenTime(LocalTime.of(10, 0));
//        schedule.setCloseTime(LocalTime.of(18, 0));
//        scheduleDao.saveSchedule(schedule);
//
//        // Получаем и выводим все расписания
//        System.out.println("Все расписания:");
//        scheduleDao.getAllSchedules().forEach(System.out::println);
//
//        // Получаем расписание по ID
//        Schedule retrievedSchedule = scheduleDao.getScheduleById(schedule.getId());
//        System.out.println("Найденное расписание: " + retrievedSchedule);
//    }
//}