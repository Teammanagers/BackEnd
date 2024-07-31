package kr.teammanagers.calendar.application;

import kr.teammanagers.calendar.dto.response.GetSimpleCalendarList;

public interface CalendarQueryService {
    GetSimpleCalendarList getSimpleCalendarList(Long memberId, Long teamId, Integer month);
}
