package kr.teammanagers.calendar.dto;

import kr.teammanagers.calendar.domain.Calendar;
import kr.teammanagers.common.Status;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CalendarDto(
        Long calendarId,
        String title,
        Status status,
        Boolean isAlarmed,
        LocalDate date
) {
    public static CalendarDto of(Calendar calendar, Boolean isAlarmed) {
        return CalendarDto.builder()
                .calendarId(calendar.getId())
                .title(calendar.getTitle())
                .status(calendar.getStatus())
                .isAlarmed(isAlarmed)
                .date(calendar.getDate().toLocalDate())
                .build();
    }
}
