package jpa.basic.schedule.dto;

import jpa.basic.schedule.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;

public record ScheduleAndCommentReadResponseDto(
        Long id, String title, String name, String content, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponseDto> comments)
{
    public ScheduleAndCommentReadResponseDto(Schedule schedule, List<CommentResponseDto> comments) {
        this(schedule.getId(), schedule.getTitle(), schedule.getName(), schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt(), comments);
    }
}
