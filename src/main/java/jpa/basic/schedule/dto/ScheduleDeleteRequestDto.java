package jpa.basic.schedule.dto;

public record ScheduleDeleteRequestDto(
        String password
) {
    public ScheduleDeleteRequestDto {
        if (password == null || password.length() > 20 || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        }
    }
}
