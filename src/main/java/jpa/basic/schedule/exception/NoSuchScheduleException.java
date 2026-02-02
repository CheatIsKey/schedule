package jpa.basic.schedule.exception;

public class NoSuchScheduleException extends RuntimeException {
    public NoSuchScheduleException() {
    }

    public NoSuchScheduleException(String message) {
        super(message);
    }
}
