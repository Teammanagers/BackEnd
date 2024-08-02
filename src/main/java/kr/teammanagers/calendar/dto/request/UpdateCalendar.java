package kr.teammanagers.calendar.dto.request;

import java.util.List;

public record UpdateCalendar(
        String title,
        List<Long> participants,
        String content
) {
}
