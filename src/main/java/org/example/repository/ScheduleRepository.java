package org.example.repository;

import org.example.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAttractionId(Long attractionId); // Найти расписания по аттракциону
    List<Schedule> findByDayOfWeek(String dayOfWeek); // Найти расписания по дню недели
}
