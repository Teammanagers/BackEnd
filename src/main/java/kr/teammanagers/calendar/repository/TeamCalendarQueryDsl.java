package kr.teammanagers.calendar.repository;

import kr.teammanagers.calendar.domain.Calendar;

import java.util.List;

public interface TeamCalendarQueryDsl {
    List<Calendar> findAllCalendarByMemberIdAndTeamIdAndMonth(Long memberId, Long teamId, Integer month);
}
