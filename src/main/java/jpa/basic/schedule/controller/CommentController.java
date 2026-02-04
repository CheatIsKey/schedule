package jpa.basic.schedule.controller;

import jpa.basic.schedule.dto.CommentCreateRequestDto;
import jpa.basic.schedule.dto.CommentCreateResponseDto;
import jpa.basic.schedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/api/comments/{scheduleId}")
    public ResponseEntity<CommentCreateResponseDto> createComment(@PathVariable Long scheduleId,
                                                                  @RequestBody CommentCreateRequestDto commentCreateRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(scheduleId, commentCreateRequestDto));
    }
}
