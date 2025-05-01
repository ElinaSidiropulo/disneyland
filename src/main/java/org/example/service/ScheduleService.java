package org.example.service;

import org.example.entity.Attraction;
import org.example.entity.Schedule;
import org.example.repository.AttractionRepository;
import org.example.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final AttractionRepository attractionRepository; // Добавляем репозиторий для аттракционов

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, AttractionRepository attractionRepository) {
        this.scheduleRepository = scheduleRepository;
        this.attractionRepository = attractionRepository;
    }

    public Schedule saveSchedule(Schedule schedule) {
        // Загружаем аттракцион по ID, если он передан в объекте schedule
        if (schedule.getAttraction() != null && schedule.getAttraction().getId() != null) {
            Optional<Attraction> attraction = attractionRepository.findById(schedule.getAttraction().getId());
            if (attraction.isPresent()) {
                schedule.setAttraction(attraction.get());
            } else {
                throw new IllegalArgumentException("Attraction with ID " + schedule.getAttraction().getId() + " not found");
            }
        }
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElse(null);
    }

    public List<Schedule> getSchedulesByAttractionId(Long attractionId) {
        return scheduleRepository.findByAttractionId(attractionId);
    }

    public List<Schedule> getSchedulesByDayOfWeek(String dayOfWeek) {
        return scheduleRepository.findByDayOfWeek(dayOfWeek);
    }

    // Обновление расписания
    public Schedule updateSchedule(Long id, Schedule schedule) {
        if (scheduleRepository.existsById(id)) {
            schedule.setId(id);
            return scheduleRepository.save(schedule);
        } else {
            return null; // Если расписание с таким ID не найдено
        }
    }

    // Удаление расписания
    public boolean deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
            return true;
        } else {
            return false; // Если расписание с таким ID не найдено
        }
    }
}
