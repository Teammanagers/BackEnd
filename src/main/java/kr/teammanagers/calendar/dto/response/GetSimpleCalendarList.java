package kr.teammanagers.calendar.dto.response;

import kr.teammanagers.calendar.dto.CalendarDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetSimpleCalendarList(
        List<CalendarDto> calendarListOfMonth
) {
    public static GetSimpleCalendarList from(List<CalendarDto> calendarListOfMonth) {
        return GetSimpleCalendarList.builder()
                .calendarListOfMonth(calendarListOfMonth)
                .build();
    }
}
