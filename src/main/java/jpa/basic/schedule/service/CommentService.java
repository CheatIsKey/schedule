package jpa.basic.schedule.service;

import jpa.basic.schedule.domain.Comment;
import jpa.basic.schedule.dto.CommentCreateRequestDto;
import jpa.basic.schedule.dto.CommentCreateResponseDto;
import jpa.basic.schedule.exception.*;
import jpa.basic.schedule.repository.CommentRepository;
import jpa.basic.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public CommentCreateResponseDto addComment(Long scheduleId, CommentCreateRequestDto commentCreateRequestDto) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        long commentsCnt = commentRepository.countByScheduleId(scheduleId);

        if (commentsCnt >= 10) {
            throw new CustomException(ErrorCode.COMMENT_LIMIT_EXCEEDED);
        }

        /**
         * saved는 scheduleId가 존재하는 상태
         * new Comment()의 scheduleId는 null 상태
         */
        Comment saved = commentRepository.save(new Comment(scheduleId, commentCreateRequestDto.content(),
                commentCreateRequestDto.name(), commentCreateRequestDto.password()));

        return new CommentCreateResponseDto(saved);
    }
}
