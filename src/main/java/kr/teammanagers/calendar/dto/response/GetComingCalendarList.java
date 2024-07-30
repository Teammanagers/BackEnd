package kr.teammanagers.calendar.dto.response;

import kr.teammanagers.calendar.dto.CalendarDto;

import java.util.List;

public record GetComingCalendarList(
        List<CalendarDto> comingCalendarList
) {
}
