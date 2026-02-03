package jpa.basic.schedule.dto;

public record CommentCreateRequestDto(
        String name, String content, String password
) {
    public CommentCreateRequestDto {
        if (name == null || name.length() > 20 || name.isBlank()) {
            throw new IllegalArgumentException("작성자명을 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        }
        if (content == null || content.length() > 100 || content.isBlank()) {
            throw new IllegalArgumentException("댓글 내용을 최소(1) ~ 최대(100) 범위에 맞게 작성하세요!");
        }
        if (password == null || password.length() > 20 || password.isBlank()) {
            throw new IllegalArgumentException("비밀번호를 최소(1) ~ 최대(20) 범위에 맞게 작성하세요!");
        }
    }
}
