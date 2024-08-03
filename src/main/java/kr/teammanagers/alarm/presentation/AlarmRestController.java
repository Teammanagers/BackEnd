package kr.teammanagers.alarm.presentation;

import kr.teammanagers.alarm.application.AlarmCommandService;
import kr.teammanagers.alarm.dto.request.CreateAlarm;
import kr.teammanagers.common.payload.code.ApiPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlarmRestController {

    private final AlarmCommandService alarmCommandService;

    @PostMapping("/team/{teamId}/alarm/{referenceId}")
    public ApiPayload<Void> createAlarm(@RequestBody final CreateAlarm request,
                                        @PathVariable(name = "teamId") final Long teamId,
                                        @PathVariable(name = "referenceId") final Long referenceId) {

        alarmCommandService.createAlarm(request, 1L, teamId, referenceId);

        return ApiPayload.onSuccess();
    }
}
