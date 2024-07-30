package kr.teammanagers.calendar.presentation;

import kr.teammanagers.calendar.application.CalendarCommandService;
import kr.teammanagers.calendar.dto.request.CreateCalendar;
import kr.teammanagers.common.payload.code.ApiPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalendarRestController {

    private final CalendarCommandService calendarCommandService;

    @PostMapping("/team/{teamId}/calendar")
    public ApiPayload<Void> create(@RequestBody final CreateCalendar request,
                                   @PathVariable(name = "teamId") Long teamId) {

        calendarCommandService.createCalendar(request, 1L, teamId);     //Todo: memberId 수정

        return ApiPayload.onSuccess();
    }
}
