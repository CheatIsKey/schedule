package jpa.basic.schedule.controller;

import jpa.basic.schedule.dto.ScheduleRequestDto;
import jpa.basic.schedule.dto.ScheduleResponseDto;
import jpa.basic.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/api")
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        scheduleService.
    }

    @GetMapping("/api")
    public List<ScheduleResponseDto> getSchedules(@RequestParam(required = false) String name) {
        return scheduleService.getSchedule(name);
    }

//    @PostMapping("/api/")
//    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto dto) {
//
//    }
//
//    @PatchMapping("/api/{scheduleId}")
//
//    @DeleteMapping("/api/{scheduleId")
}
