package jpa.basic.schedule.controller;

import jpa.basic.schedule.dto.*;
import jpa.basic.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 일정 생성
     *
     * @param scheduleCreateRequestDto : 클라이언트로부터 전달받은 일정 요청 DTO
     * @return : 기본키(id)와 생성일자, 수정일자를 포함한 일정 응답 DTO 반환
     */
    @PostMapping("/api/schedules")
    public ResponseEntity<ScheduleCreateResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto scheduleCreateRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.addSchedule(scheduleCreateRequestDto));
    }

    /**
     * 전체 일정 조회
     * 수정일 기준 내림차순 정렬 && 비밀번호는 제외하고 반환하기
     *
     * @param name : 작성자명
     * @return : 작성자명을 기준으로 등록된 일정 전부 조회 && 작성자명은 포함되거나 안될 수 있다.
     */
    @GetMapping("/api/schedules")
    public ResponseEntity<List<ScheduleReadAllResponseDto>> getSchedules(@RequestParam(required = false) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedules(name));
    }

    /**
     * 특정 일정 조회
     *
     * @param scheduleId : 조회할 일정 기본키
     * @return : 조회된 일정을 DTO로 반환
     */
//    @GetMapping("/api/{id}")
//    public ResponseEntity<ScheduleReadResponseDto> getSchedule(@PathVariable Long id) {
//        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getScheduleById(id));
//    }
    @GetMapping("/api/schedules/{scheduleId}")
    public ResponseEntity<ScheduleAndCommentReadResponseDto> getScheduleAndComment(@PathVariable Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getScheduleAndCommentById(scheduleId));
    }

    /**
     * 특정 일정의 제목 혹은 작성자명 수정
     *
     * @param scheduleId : 업데이트할 일정 id
     * @param scheduleUpdateRequestDto : 사용자로부터 전달받은 변경 요청 일정 DTO
     * @return : 변경된 일정 응답 DTO
     */
    @PutMapping("/api/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponseDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        ScheduleUpdateResponseDto result = scheduleService.updateSchedule(scheduleId, scheduleUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 특정 일정을 삭제
     *
     * @param scheduleId : 일정 객체를 조회할 수 있는 기본키(id)
     * @param scheduleDeleteRequestDto : 삭제할 일정의 비밀번호 검증을 위한 dto
     */
    @DeleteMapping("/api/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleDeleteRequestDto scheduleDeleteRequestDto) {
        scheduleService.deleteScheduleById(scheduleId, scheduleDeleteRequestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
