package kr.teammanagers.calendar.dto.request;

import java.util.List;

public record UpdateCalendar(
        String title,
        List<String> participants,
        String content
) {
}
