package jpa.basic.schedule.dto;

public record CustomExceptionDto(
        int status,
        String code,
        String message
) {
}
