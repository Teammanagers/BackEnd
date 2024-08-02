package kr.teammanagers.calendar.dto.response;

import kr.teammanagers.calendar.dto.CalendarDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetComingCalendarList(
        List<CalendarDto> comingCalendarList
) {
    public static GetComingCalendarList from(List<CalendarDto> comingCalendarList) {
        return GetComingCalendarList.builder()
                .comingCalendarList(comingCalendarList)
                .build();
    }
}
