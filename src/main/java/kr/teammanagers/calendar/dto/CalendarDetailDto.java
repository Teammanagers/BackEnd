package kr.teammanagers.calendar.dto;

import java.util.List;

public record CalendarDetailDto(
        Long calendarId,
        String title,
        String content,
        List<String> participants,
        Boolean isAlarmed
) {
}
