package jpa.basic.schedule.dto;

import jpa.basic.schedule.domain.Comment;

import java.time.LocalDateTime;

public record CommentCreateResponseDto(
        Long id, Long scheduleId, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt
) {
    public CommentCreateResponseDto(Comment comment) {
        this(comment.getId(), comment.getScheduleId(), comment.getName(), comment.getContent(), comment.getCreatedAt(), comment.getModifiedAt());
    }
}
