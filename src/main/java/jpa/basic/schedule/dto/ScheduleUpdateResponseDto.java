package jpa.basic.schedule.dto;

import jpa.basic.schedule.domain.Schedule;

import java.time.LocalDateTime;

public record ScheduleUpdateResponseDto(
        Long id, String title, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt
) {
    public ScheduleUpdateResponseDto(Schedule schedule) {
        this(schedule.getId(), schedule.getTitle(), schedule.getName(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
