package org.example.util;

import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

            // ЯВНО добавляем все сущности
            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Ticket.class);
            configuration.addAnnotatedClass(Attraction.class);
            configuration.addAnnotatedClass(Booking.class);
            configuration.addAnnotatedClass(Review.class);
            configuration.addAnnotatedClass(Visit.class);
            configuration.addAnnotatedClass(Schedule.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
