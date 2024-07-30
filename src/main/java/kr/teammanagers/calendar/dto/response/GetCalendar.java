package kr.teammanagers.calendar.dto.response;

import kr.teammanagers.calendar.dto.CalendarDetailDto;

public record GetCalendar(
        CalendarDetailDto calendar
) {
}
