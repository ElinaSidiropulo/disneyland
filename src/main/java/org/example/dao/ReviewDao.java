//package org.example.dao;
//
//import org.example.entity.Review;
//import org.example.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class ReviewDao {
//    private static final Logger logger = LoggerFactory.getLogger(ReviewDao.class);
//
//    // Сохранение отзыва
//    public void saveReview(Review review) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.persist(review);  // Сохранение отзыва
//            transaction.commit();
//            logger.info("Отзыв сохранен: {}", review);
//        } catch (Exception e) {
//            if (transaction != null && transaction.getStatus().canRollback()) {
//                transaction.rollback();
//            }
//            logger.error("Ошибка при сохранении отзыва: ", e);
//            throw new RuntimeException("Ошибка при сохранении отзыва", e);
//        }
//    }
//
//    // Получение всех отзывов
//    public List<Review> getAllReviews() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Review> query = session.createQuery("FROM Review", Review.class);
//            List<Review> reviews = query.list();
//            logger.info("Получено отзывов: {}", reviews.size());
//            return reviews;
//        } catch (Exception e) {
//            logger.error("Ошибка при получении списка отзывов: ", e);
//            throw new RuntimeException("Ошибка при получении списка отзывов", e);
//        }
//    }
//
//    // Получение отзыва по ID
//    public Review getReviewById(Long id) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Review.class, id);
//        } catch (Exception e) {
//            logger.error("Ошибка при получении отзыва по ID: ", e);
//            return null;
//        }
//    }
//
//    // Получение отзывов пользователя по ID
//    public List<Review> getReviewsByUserId(Long userId) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Review> query = session.createQuery("FROM Review WHERE user.id = :userId", Review.class);
//            query.setParameter("userId", userId);
//            List<Review> reviews = query.list();
//            logger.info("Получено отзывов для пользователя с ID {}: {}", userId, reviews.size());
//            return reviews;
//        } catch (Exception e) {
//            logger.error("Ошибка при получении отзывов пользователя с ID {}: ", userId, e);
//            throw new RuntimeException("Ошибка при получении отзывов пользователя", e);
//        }
//    }
//
//    // Получение отзывов по аттракциону
//    public List<Review> getReviewsByAttractionId(Long attractionId) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Review> query = session.createQuery("FROM Review WHERE attraction.id = :attractionId", Review.class);
//            query.setParameter("attractionId", attractionId);
//            List<Review> reviews = query.list();
//            logger.info("Получено отзывов для аттракциона с ID {}: {}", attractionId, reviews.size());
//            return reviews;
//        } catch (Exception e) {
//            logger.error("Ошибка при получении отзывов аттракциона с ID {}: ", attractionId, e);
//            throw new RuntimeException("Ошибка при получении отзывов аттракциона", e);
//        }
//    }
//}
