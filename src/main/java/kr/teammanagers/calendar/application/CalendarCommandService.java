package kr.teammanagers.calendar.application;

import kr.teammanagers.calendar.dto.request.CreateCalendar;

public interface CalendarCommandService {
    void createCalendar(CreateCalendar request, Long teamId);
}
