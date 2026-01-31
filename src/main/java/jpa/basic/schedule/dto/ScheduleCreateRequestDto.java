package jpa.basic.schedule.dto;

public record ScheduleCreateRequestDto(
        String title, String content, String name, String password
) {
}
