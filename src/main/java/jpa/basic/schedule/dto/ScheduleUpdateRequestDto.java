package jpa.basic.schedule.dto;

public record ScheduleUpdateRequestDto(
        String title, String content, String name, String password) {
}
