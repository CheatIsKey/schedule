package jpa.basic.schedule.dto;

import jpa.basic.schedule.domain.Comment;

public record CommentCreateRequestDto(
        String name, String content, String password
) {
    public CommentCreateRequestDto(Comment comment) {
        this(comment.getName(), comment.getContent(), comment.getPassword());
    }
}
