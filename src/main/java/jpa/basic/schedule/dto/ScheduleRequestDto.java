package jpa.basic.schedule.dto;

import java.time.LocalDateTime;

public record ScheduleRequestDto(
        String title, String content, String name, String password, LocalDateTime createdAt, LocalDateTime modifiedAt) {
}
