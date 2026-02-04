package jpa.basic.schedule.service;

import jpa.basic.schedule.domain.Comment;
import jpa.basic.schedule.domain.Schedule;
import jpa.basic.schedule.dto.*;
import jpa.basic.schedule.exception.NoSuchScheduleException;
import jpa.basic.schedule.exception.PasswordMismatchException;
import jpa.basic.schedule.repository.CommentRepository;
import jpa.basic.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository repository;
    private final CommentRepository commentRepository;

    /**
     * 일정 생성
     *
     * @param scheduleCreateRequestDto : 일정 생성 요청 DTO
     * @return : 비밀번호를 제외한 일정 DTO
     */
    public ScheduleCreateResponseDto addSchedule(ScheduleCreateRequestDto scheduleCreateRequestDto) {
        Schedule schedule = new Schedule(
                scheduleCreateRequestDto.title(),
                scheduleCreateRequestDto.content(),
                scheduleCreateRequestDto.name(),
                scheduleCreateRequestDto.password()
        );

        Schedule savedSchedule = repository.save(schedule);

        return new ScheduleCreateResponseDto(savedSchedule.getId(), savedSchedule.getTitle(),
                savedSchedule.getName(), savedSchedule.getContent(), savedSchedule.getCreatedAt(), savedSchedule.getModifiedAt());
    }

    /**
     * 특정 사용자의 일정 전체 조회
     * 1. name이 비어있거나 공백만 있으면 전체 조회
     * 2. name으로 작성된 일정 조회
     * 수정일 기준 내림차순 정렬
     *
     * @param name : 검색할 작성자명
     * @return : 일정 목록을 리스트로 반환
     */
    @Transactional(readOnly = true)
    public List<ScheduleReadAllResponseDto> getSchedules(String name) {
        List<Schedule> schedules;

        if (name == null || name.isBlank()) {
            schedules = repository.findAllByOrderByModifiedAtDesc();
        } else {
            schedules = repository.findByNameOrderByModifiedAtDesc(name);
        }

        return schedules.stream()
                .map(ScheduleReadAllResponseDto::new)
                .toList();
    }

    /**
     * 특정 일정 조회
     *
     * @param scheduleId : 검색할 일정 기본키
     * @return : 일정 단일 객체 DTO 반환
     */
    @Transactional(readOnly = true)
    public ScheduleReadResponseDto getScheduleById(Long scheduleId) {
        return repository.findById(scheduleId)
                .map(ScheduleReadResponseDto::new)
                .orElseThrow(() -> new NoSuchScheduleException("예정된 일정이 없습니다."));
    }

    @Transactional(readOnly = true)
    public ScheduleAndCommentReadResponseDto getScheduleAndCommentById(Long scheduleId) {
        Schedule schedule = repository.findById(scheduleId)
                .orElseThrow(() -> new NoSuchScheduleException("예정된 일정이 없습니다."));

        List<CommentResponseDto> comments = commentRepository.findCommentsByScheduleId(scheduleId).stream()
                .map(CommentResponseDto::new)
                .toList();

        return new ScheduleAndCommentReadResponseDto(schedule, comments);
    }

    // TODO: DTO를 무조건 하나의 책임만 주기
    /**
     * 수정할 일정 DTO와 검증할 비밀번호를 컨트롤러에게 전달받고 검증을 거쳐서 더티체킹으로 변경사항을 관리한다.
     *
     * @param scheduleId : 변경할 일정 기본키
     * @param scheduleUpdateRequestDto : 클라이언트가 요청한 변경사항이 담긴 일정 DTO
     * @return : 변경사항이 적용된 응답 일정 DTO 반환
     */
    public ScheduleUpdateResponseDto updateSchedule(Long scheduleId, ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Schedule schedule = repository.findById(scheduleId)
                .orElseThrow(() -> new NoSuchScheduleException("해당 일정은 존재하지 않습니다."));

        String password = scheduleUpdateRequestDto.password();

        if (schedule.getPassword().equals(password)) {
//            Schedule schedule = new Schedule(scheduleRequestDto.title(), scheduleRequestDto.content(),
//                    scheduleRequestDto.name(), scheduleRequestDto.password());

            /**
             * 더티체킹으로 변경사항 관리
             */
            schedule.changeScheduleName(scheduleUpdateRequestDto.name());
            schedule.changeScheduleTitle(scheduleUpdateRequestDto.title());

            return new ScheduleUpdateResponseDto(schedule);
        } else {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
    }

    /**
     * 일정 객체의 기본키(scheduleId)를 통해 조회 및 비밀번호 검증 후 삭제하는 메서드
     *
     * @param scheduleId       : 삭제하려는 일정의 기본키(scheduleId)
     * @param scheduleDeleteRequestDto : 클라이언트가 요청한 삭제 id가 담긴 일정 DTO
     */
    public void deleteScheduleById(Long scheduleId, ScheduleDeleteRequestDto scheduleDeleteRequestDto) {
        Schedule schedule = repository.findById(scheduleId)
                .orElseThrow(() -> new NoSuchScheduleException("해당 일정이 존재하지 않습니다."));

        if (!schedule.getPassword().equals(scheduleDeleteRequestDto.password())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        repository.delete(schedule);

//        findById()를 쓸 수도 있지만, 조회가 아닌 존재 유무를 따지기 때문에
//        existsById()로 더 가볍게 체크할 수 있다.
//        boolean exists = repository.existsById(scheduleId);
//
//        if (!exists) {
//            throw new NoSuchScheduleException("해당 일정은 존재하지 않습니다.");
//        } else {
//            repository.deleteById(scheduleId);
//        }
    }
}
