package jpa.basic.schedule.dto;

import java.time.LocalDateTime;

public record ScheduleCreateResponseDto(
        Long id, String title, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
