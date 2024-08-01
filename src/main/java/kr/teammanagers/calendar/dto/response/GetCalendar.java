package kr.teammanagers.calendar.dto.response;

import kr.teammanagers.calendar.dto.CalendarDetailDto;
import lombok.Builder;

@Builder
public record GetCalendar(
        CalendarDetailDto calendar
) {
    public static GetCalendar from(CalendarDetailDto calendar) {
        return GetCalendar.builder()
                .calendar(calendar)
                .build();
    }
}
