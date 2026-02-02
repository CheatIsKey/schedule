package jpa.basic.schedule.exception;

public class CommentLimitExceededException extends RuntimeException {
    public CommentLimitExceededException() {
    }

    public CommentLimitExceededException(String message) {
        super(message);
    }
}
