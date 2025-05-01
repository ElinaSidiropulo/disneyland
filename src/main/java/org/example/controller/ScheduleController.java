package org.example.controller;

import org.example.entity.Schedule;
import org.example.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        try {
            return scheduleService.saveSchedule(schedule);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping
    public List<Schedule> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/{id}")
    public Schedule getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    @GetMapping("/attraction/{attractionId}")
    public List<Schedule> getSchedulesByAttractionId(@PathVariable Long attractionId) {
        return scheduleService.getSchedulesByAttractionId(attractionId);
    }

    @GetMapping("/day/{dayOfWeek}")
    public List<Schedule> getSchedulesByDayOfWeek(@PathVariable String dayOfWeek) {
        return scheduleService.getSchedulesByDayOfWeek(dayOfWeek);
    }

    // Обновление расписания
    @PutMapping("/{id}")
    public Schedule updateSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(id, schedule);
    }

    // Удаление расписания
    @DeleteMapping("/{id}")
    public boolean deleteSchedule(@PathVariable Long id) {
        return scheduleService.deleteSchedule(id);
    }
}
