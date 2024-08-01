package kr.teammanagers.calendar.presentation;

import kr.teammanagers.calendar.application.CalendarCommandService;
import kr.teammanagers.calendar.application.CalendarQueryService;
import kr.teammanagers.calendar.dto.request.CreateCalendar;
import kr.teammanagers.calendar.dto.request.UpdateCalendar;
import kr.teammanagers.calendar.dto.response.GetCalendar;
import kr.teammanagers.calendar.dto.response.GetComingCalendarList;
import kr.teammanagers.calendar.dto.response.GetSimpleCalendarList;
import kr.teammanagers.common.payload.code.ApiPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalendarRestController {

    private final CalendarCommandService calendarCommandService;
    private final CalendarQueryService calendarQueryService;

    @PostMapping("/team/{teamId}/calendar")
    public ApiPayload<Void> create(@RequestBody final CreateCalendar request,
                                   @PathVariable(name = "teamId") final Long teamId) {

        calendarCommandService.createCalendar(request, teamId);

        return ApiPayload.onSuccess();
    }

    @GetMapping("/team/{teamId}/calendar")
    public ApiPayload<GetSimpleCalendarList> getSimpleCalendarList(@PathVariable(name = "teamId") final Long teamId,
                                                                   @RequestParam(name = "month") final Integer month) {

        GetSimpleCalendarList getSimpleCalendarList = calendarQueryService.getCalendarListOfMonth(1L, teamId, month);

        return ApiPayload.onSuccess(getSimpleCalendarList);
    }

    @GetMapping("team/{teamId}/calendar/coming")
    public ApiPayload<GetComingCalendarList> getComingCalendarList(@PathVariable(name = "teamId") final Long teamId) {

        GetComingCalendarList getComingCalendarList = calendarQueryService.getComingCalendarList(1L, teamId);

        return ApiPayload.onSuccess(getComingCalendarList);
    }

    @GetMapping("/calendar/{calendarId}")
    public ApiPayload<GetCalendar> getCalendar(@PathVariable(name = "calendarId") final Long calendarId) {

        GetCalendar getCalendar = calendarQueryService.getCalendarDetail(calendarId);

        return ApiPayload.onSuccess(getCalendar);
    }

    @PatchMapping("/calendar/{calendarId}")
    public ApiPayload<Void> updateCalendar(@RequestBody final UpdateCalendar request,
                                           @PathVariable(name = "calendarId") final Long calendarId) {

        calendarCommandService.update(request, calendarId);

        return ApiPayload.onSuccess();
    }

    @PatchMapping("/calendar/{calendarId}/state")
    public ApiPayload<Void> updateCalendarState(@PathVariable(name = "calendarId") final Long calendarId) {

        calendarCommandService.updateState(calendarId);

        return ApiPayload.onSuccess();
    }

    @DeleteMapping("/calendar/{calendarId}")
    public ApiPayload<Void> deleteCalendar(@PathVariable(name = "calendarId") final Long calendarId) {

        calendarCommandService.delete(calendarId);

        return ApiPayload.onSuccess();
    }

}
