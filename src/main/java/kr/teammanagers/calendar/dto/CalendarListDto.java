package kr.teammanagers.calendar.dto;

import java.time.LocalDate;
import java.util.List;

public record CalendarListDto(
        LocalDate date,
        List<CalendarDto> calendarList
) {
}
