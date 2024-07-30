package kr.teammanagers.calendar.dto.request;

import java.time.LocalDate;
import java.util.List;

public record CreateCalendar(
        LocalDate date,
        String title,
        List<String> participants,
        String content
) {
}
