package jpa.basic.schedule.dto;

public record CommentCreateRequestDto(
        String name, String content, String password
){}
