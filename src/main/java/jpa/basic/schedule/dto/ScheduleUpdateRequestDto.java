package jpa.basic.schedule.dto;

public record ScheduleUpdateRequestDto(
        Long id, String title, String content, String name, String password) {
}
