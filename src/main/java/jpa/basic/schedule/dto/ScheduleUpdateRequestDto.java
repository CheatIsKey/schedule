package jpa.basic.schedule.dto;

public record ScheduleUpdateRequestDto(
        String title, String content, String name, String password) {

    public ScheduleUpdateRequestDto {
        if (title == null || title.length() > 30 || title.isBlank()) {
            throw new IllegalArgumentException("일정 제목을 최소(1) ~ 최대(30) 범위에 맞게 작성하세요!");
        }
        if (content == null || content.length() > 200 || content.isBlank()) {
            throw new IllegalArgumentException("일정 내용을 최소(1) ~ 최대(200) 범위에 맞게 작성하세요!");
        }
        if (name == null || name.length() > 20 || name.isBlank()) {
            throw new IllegalArgumentException("작성자명을 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        }
        if (password == null || password.length() > 20 || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        }
    }
}
