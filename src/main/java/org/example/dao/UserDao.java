package org.example.dao;

import org.example.entity.User;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            logger.info("Пользователь сохранен: {}", user);
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            logger.error("Ошибка при сохранении пользователя: {}", e.getMessage(), e);
            throw new RuntimeException("Ошибка при сохранении пользователя", e);
        }
    }

    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            List<User> users = query.list();
            logger.info("Получено пользователей: {}", users.size());
            return users;
        } catch (Exception e) {
            logger.error("Ошибка при получении списка пользователей: {}", e.getMessage(), e);
            throw new RuntimeException("Ошибка при получении списка пользователей", e);
        }
    }

    public User getUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                logger.info("Найден пользователь с ID {}: {}", id, user);
            } else {
                logger.warn("Пользователь с ID {} не найден", id);
            }
            return user;
        } catch (Exception e) {
            logger.error("Ошибка при получении пользователя с ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Ошибка при получении пользователя", e);
        }
    }
}
