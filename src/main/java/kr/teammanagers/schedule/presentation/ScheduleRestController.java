package kr.teammanagers.schedule.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.schedule.application.ScheduleCommandService;
import kr.teammanagers.schedule.application.ScheduleQueryService;
import kr.teammanagers.schedule.dto.request.CreateSchedule;
import kr.teammanagers.schedule.dto.request.UpdateSchedule;
import kr.teammanagers.schedule.dto.response.GetTeamSchedule;
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
                                           @PathVariable(name = "teamId") final Long teamId) {

        scheduleCommandService.create(1L, teamId, request);

        return ApiPayload.onSuccess();
    }

    @PatchMapping("/team/{teamId}/schedule")
    public ApiPayload<Void> updateSchedule(@RequestBody final UpdateSchedule request,
                                           @PathVariable(name = "teamId") final Long teamId) {

        scheduleCommandService.update(1L, teamId, request);

        return ApiPayload.onSuccess();
    }

    @DeleteMapping("/team/{teamId}/schedule")
    public ApiPayload<Void> deleteSchedule(@PathVariable(name = "teamId") final Long teamId) {

        scheduleCommandService.delete(1L, teamId);

        return ApiPayload.onSuccess();
    }

    @GetMapping("team/{teamId}/schedule")
    public ApiPayload<GetTeamSchedule> getTeamSchedule(@PathVariable(name = "teamId") final Long teamId) {

        GetTeamSchedule getTeamSchedule = scheduleQueryService.getTeamSchedule(teamId);

        return ApiPayload.onSuccess(getTeamSchedule);
    }
}