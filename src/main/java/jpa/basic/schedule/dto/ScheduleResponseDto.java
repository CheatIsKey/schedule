package jpa.basic.schedule.dto;

import java.time.LocalDateTime;

public record ScheduleResponseDto(
        Long id, String title, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {

}
