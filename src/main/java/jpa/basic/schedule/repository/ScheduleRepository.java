package jpa.basic.schedule.repository;

import jpa.basic.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByNameOrderByModifiedAtDesc(String name);

    List<Schedule> findAllByOrderByModifiedAtDesc();
}
