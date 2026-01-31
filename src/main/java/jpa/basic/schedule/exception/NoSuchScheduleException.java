package jpa.basic.schedule.exception;

public class NoSuchScheduleException extends RuntimeException {
    public NoSuchScheduleException(String message) {
        super(message);
    }
}
