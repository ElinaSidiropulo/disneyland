//package org.example.dao;
//
//import org.example.entity.Schedule;
//import org.example.util.HibernateUtil;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class ScheduleDao {
//    private static final Logger logger = LoggerFactory.getLogger(ScheduleDao.class);
//
//    public void saveSchedule(Schedule schedule) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.persist(schedule);
//            transaction.commit();
//            logger.info("Расписание сохранено: {}", schedule);
//        } catch (Exception e) {
//            if (transaction != null && transaction.getStatus().canRollback()) {
//                try {
//                    transaction.rollback();
//                } catch (Exception rollbackEx) {
//                    logger.error("Ошибка при откате транзакции", rollbackEx);
//                }
//            }
//
//            logger.error("Ошибка при сохранении расписания: ", e);
//            throw new RuntimeException("Ошибка при сохранении расписания", e);
//        }
//    }
//
//    public List<Schedule> getAllSchedules() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Query<Schedule> query = session.createQuery("FROM Schedule", Schedule.class);
//            List<Schedule> schedules = query.list();
//            logger.info("Получено расписаний: {}", schedules.size());
//            return schedules;
//        } catch (Exception e) {
//            logger.error("Ошибка при получении списка расписаний: ", e);
//            throw new RuntimeException("Ошибка при получении списка расписаний", e);
//        }
//    }
//
//    public Schedule getScheduleById(Long id) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Schedule.class, id);
//        } catch (Exception e) {
//            logger.error("Ошибка при получении расписания: ", e);
//            return null;
//        }
//    }
//}
