package jpa.basic.schedule.dto;

import jpa.basic.schedule.domain.Comment;

import java.time.LocalDateTime;

public record CommentResponseDto(
        Long id, String content, String name, LocalDateTime createdAt, LocalDateTime modifiedAt
) {
   public CommentResponseDto(Comment comment) {
       this(comment.getId(), comment.getContent(), comment.getPassword(), comment.getCreatedAt(), comment.getModifiedAt());
   }
}
