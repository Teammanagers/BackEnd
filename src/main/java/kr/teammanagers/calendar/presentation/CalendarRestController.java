package kr.teammanagers.calendar.presentation;

import kr.teammanagers.calendar.application.CalendarCommandService;
import kr.teammanagers.calendar.dto.request.CreateCalendar;
import kr.teammanagers.calendar.dto.response.GetSimpleCalendarList;
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
                                   @PathVariable(name = "teamId") final Long teamId) {

        calendarCommandService.createCalendar(request, teamId);

        return ApiPayload.onSuccess();
    }

    @GetMapping("/team/{teamId}/calendar")
    public ApiPayload<GetSimpleCalendarList> get(@PathVariable(name = "teamId") final Long teamId,
                                                 @RequestParam(name = "month") final Integer month) {


    }

}
