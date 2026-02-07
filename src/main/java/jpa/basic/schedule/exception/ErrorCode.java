package jpa.basic.schedule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

//    공통 에러 (A###)
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "A001", "비밀번호가 일치하지 않습니다."),

//    일정 관련 에러 (S###)
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "S001", "일정을 찾을 수 없습니다."),

//    댓글 관련 에러 (C###)
    COMMENT_LIMIT_EXCEEDED(HttpStatus.BAD_REQUEST, "C001", "댓글의 최대 개수(10)가 넘었습니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;

    ErrorCode(HttpStatus status, String errorCode ,String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }
}
