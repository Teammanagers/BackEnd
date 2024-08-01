package kr.teammanagers.calendar.application;

import kr.teammanagers.calendar.domain.Calendar;
import kr.teammanagers.calendar.domain.TeamCalendar;
import kr.teammanagers.calendar.dto.CalendarDto;
import kr.teammanagers.calendar.dto.response.GetSimpleCalendarList;
import kr.teammanagers.calendar.repository.CalendarRepository;
import kr.teammanagers.calendar.repository.TeamCalendarRepository;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarQueryServiceImpl implements CalendarQueryService {

    private final CalendarRepository calendarRepository;
    private final TeamCalendarRepository teamCalendarRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public GetSimpleCalendarList getCalendarListOfMonth(Long memberId, Long teamId, Integer month) {
        List<CalendarDto> teamCalendarList = calendarRepository.findAllByTeamId(teamId).stream()
                .filter(calendar -> calendar.getDate().getMonthValue() == month)
                .map(calendar -> { return CalendarDto.of(calendar, null); })
                .toList();

        return GetSimpleCalendarList.from(teamCalendarList);
    }
}
