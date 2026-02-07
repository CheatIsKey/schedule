package jpa.basic.schedule.controller;

import jpa.basic.schedule.dto.CustomExceptionDto;
import jpa.basic.schedule.exception.CustomException;
import jpa.basic.schedule.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<CustomExceptionDto> scheduleException(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(new CustomExceptionDto(
                        errorCode.getStatus().value(),
                        errorCode.getErrorCode(),
                        errorCode.getMessage()
                )
        );
    }

//    @ExceptionHandler(value = CommentException.class)
//    public ResponseEntity<CommentExceptionDto> commentException(CommentException exception) {
//        ErrorCode errorCode = exception.getErrorCode();
//
//        return ResponseEntity.status(errorCode.getStatus())
//                .body(new CommentExceptionDto(
//                                errorCode.getStatus().value(),
//                                errorCode.getErrorCode(),
//                                errorCode.getMessage()
//                        )
//                );
//    }
}
