package jpa.basic.schedule.service;

import jpa.basic.schedule.domain.Schedule;
import jpa.basic.schedule.dto.ScheduleResponseDto;
import jpa.basic.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;

    public ScheduleResponseDto addSchedule(Schedule schedule) {
        Schedule savedSchedule = repository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getName(), savedSchedule.getContent())
    }

    /**
     * 특정 사용자의 일정 전체 조회
     *  1. name이 비어있거나 공백만 있으면 전체 조회
     *  2. name으로 작성된 일정 조회
     *  수정일 기준 내림차순 정렬
     * @param name : 검색할 작성자명
     * @return : 일정 목록을 리스트로 반환
     */
    public List<ScheduleResponseDto> getSchedule(String name) {
        if (name == null || name.isBlank()) {
            List<Schedule> schedules = repository.findAll();

            return schedules.stream()
                    .map(schedule ->
                            new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getName(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()))
                    .toList();
        } else {
            List<Schedule> schedules = repository.findByName(name);

            List<ScheduleResponseDto> dtos = schedules.stream()
                    .map(schedule ->
                            new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getName(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt()))
                    .toList();

            dtos.sort(Comparator.comparing(schedule -> schedule.modifiedAt(), Comparator.reverseOrder()));

            return dtos;
        }
    }
}
