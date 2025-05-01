//package org.example.dao;
//
//import org.example.entity.Attraction;
//import org.example.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class AttractionDao {
//    private static final Logger logger = LoggerFactory.getLogger(AttractionDao.class);
//
//    public void saveAttraction(Attraction attraction) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.persist(attraction);
//            transaction.commit();
//            logger.info("Аттракцион сохранен: {}", attraction);
//        } catch (Exception e) {
//            if (transaction != null && transaction.getStatus().canRollback()) {
//                transaction.rollback();
//            }
//            logger.error("Ошибка при сохранении аттракциона: ", e);
//            throw new RuntimeException("Ошибка при сохранении аттракциона", e);
//        }
//    }
//
//    public List<Attraction> getAllAttractions() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Attraction> query = session.createQuery("FROM Attraction", Attraction.class);
//            List<Attraction> attractions = query.list();
//            logger.info("Получено аттракционов: {}", attractions.size());
//            return attractions;
//        } catch (Exception e) {
//            logger.error("Ошибка при получении списка аттракционов: ", e);
//            throw new RuntimeException("Ошибка при получении списка аттракционов", e);
//        }
//    }
//
//    public Attraction getAttractionById(Long id) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Attraction.class, id);
//        } catch (Exception e) {
//            logger.error("Ошибка при получении аттракциона: ", e);
//            return null;
//        }
//    }
//}
