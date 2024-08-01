package kr.teammanagers.calendar.application;

import kr.teammanagers.calendar.dto.response.GetCalendar;
import kr.teammanagers.calendar.dto.response.GetSimpleCalendarList;

public interface CalendarQueryService {
    GetSimpleCalendarList getCalendarListOfMonth(Long memberId, Long teamId, Integer month);
    GetCalendar getCalendarDetail(Long calendarId);
}
