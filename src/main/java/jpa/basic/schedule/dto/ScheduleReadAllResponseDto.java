package jpa.basic.schedule.dto;

import jpa.basic.schedule.domain.Schedule;

import java.time.LocalDateTime;

public record ScheduleReadAllResponseDto(
        Long id, String title, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt
) {

    public ScheduleReadAllResponseDto(Schedule schedule) {
        this(schedule.getId(), schedule.getTitle(), schedule.getName(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }
}
