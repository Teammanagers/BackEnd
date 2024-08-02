package kr.teammanagers.schedule.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.schedule.application.ScheduleCommandService;
import kr.teammanagers.schedule.application.ScheduleQueryService;
import kr.teammanagers.schedule.dto.request.CreateSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleRestController {

    private final ScheduleCommandService scheduleCommandService;
    private final ScheduleQueryService scheduleQueryService;

    @PostMapping("/team/{teamId}/schedule")
    public ApiPayload<Void> createSchedule(@RequestBody final CreateSchedule request,
                                           @PathVariable final Long teamId) {

        scheduleCommandService.create(1L, teamId, request);

        return ApiPayload.onSuccess();
    }
}