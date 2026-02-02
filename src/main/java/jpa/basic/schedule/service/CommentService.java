package jpa.basic.schedule.service;

import jpa.basic.schedule.domain.BaseEntity;
import jpa.basic.schedule.domain.Comment;
import jpa.basic.schedule.dto.CommentCreateRequestDto;
import jpa.basic.schedule.dto.CommentCreateResponseDto;
import jpa.basic.schedule.exception.CommentLimitExceededException;
import jpa.basic.schedule.exception.NoSuchScheduleException;
import jpa.basic.schedule.repository.CommentRepository;
import jpa.basic.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService extends BaseEntity {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    public CommentCreateResponseDto addComment(Long scheduleId, CommentCreateRequestDto commentCreateRequestDto) {
        scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NoSuchScheduleException("일정이 존재하지 않습니다."));

        long commentsCnt = commentRepository.countByScheduleId(scheduleId);

        if (commentsCnt >= 10) {
            throw new CommentLimitExceededException("댓글의 최대 개수(10)가 넘었습니다.");
        }

        Comment saved = commentRepository.save(new Comment(scheduleId, commentCreateRequestDto.content(),
                commentCreateRequestDto.name(), commentCreateRequestDto.password()));

        return new CommentCreateResponseDto(saved);
    }
}
