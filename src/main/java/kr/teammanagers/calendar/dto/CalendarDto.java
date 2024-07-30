package kr.teammanagers.calendar.dto;

import kr.teammanagers.common.Status;

import java.time.LocalDate;

public record CalendarDto(
        Long calendarId,
        String title,
        Status status,
        Boolean isAlarm,
        LocalDate date
) {
}
