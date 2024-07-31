package kr.teammanagers.calendar.application;

import kr.teammanagers.calendar.domain.Calendar;
import kr.teammanagers.calendar.domain.TeamCalendar;
import kr.teammanagers.calendar.dto.request.CreateCalendar;
import kr.teammanagers.calendar.repository.CalendarRepository;
import kr.teammanagers.calendar.repository.TeamCalendarRepository;
import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.global.exception.GeneralException;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarCommandServiceImpl implements CalendarCommandService {

    private final CalendarRepository calendarRepository;
    private final TeamCalendarRepository teamCalendarRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public void createCalendar(final CreateCalendar request, final Long teamId) {
        Calendar newCalendar = request.toCalendar();
        calendarRepository.save(newCalendar);

        request.participants().stream()
                .map(memberId -> { return teamManageRepository.findByMemberIdAndTeamId(memberId, teamId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_NOT_FOUND)); })
                .forEach(teamManage -> {
                    TeamCalendar newTeamCalendar = TeamCalendar.builder().isAlarmed(false).build();
                    newTeamCalendar.setCalendar(newCalendar);
                    newTeamCalendar.setTeamManage(teamManage);
                    teamCalendarRepository.save(newTeamCalendar);
                });
    }
}
