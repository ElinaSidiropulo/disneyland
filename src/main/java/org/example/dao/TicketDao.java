package org.example.dao;

import org.example.entity.Ticket;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TicketDao {
    private static final Logger logger = LoggerFactory.getLogger(TicketDao.class);

    public void saveTicket(Ticket ticket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            logger.info("Билет сохранен: {}", ticket);
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            logger.error("Ошибка при сохранении билета: ", e);
            throw new RuntimeException("Ошибка при сохранении билета", e);
        }
    }

    public List<Ticket> getAllTickets() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Ticket", Ticket.class).list();
        } catch (Exception e) {
            logger.error("Ошибка при получении списка билетов: ", e);
            throw new RuntimeException("Ошибка при получении списка билетов", e);
        }
    }

    public Ticket getTicketById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Ticket.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
