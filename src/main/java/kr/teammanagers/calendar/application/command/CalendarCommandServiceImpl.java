package kr.teammanagers.calendar.application.command;

import kr.teammanagers.calendar.application.module.CalendarModuleService;
import kr.teammanagers.calendar.domain.Calendar;
import kr.teammanagers.calendar.domain.TeamCalendar;
import kr.teammanagers.calendar.dto.request.CreateCalendar;
import kr.teammanagers.calendar.dto.request.UpdateCalendar;
import kr.teammanagers.team.application.module.TeamModuleService;
import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.domain.TeamManage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CalendarCommandServiceImpl implements CalendarCommandService {

    private final TeamModuleService teamModuleService;
    private final CalendarModuleService calendarModuleService;

    @Override
    public void createCalendar(final CreateCalendar request, final Long teamId) {

        Team team = teamModuleService.findById(teamId, Team.class);
        Calendar newCalendar = request.toCalendar();
        newCalendar.setTeam(team);
        calendarModuleService.save(newCalendar, Calendar.class);

        request.participants()
                .forEach(teamManageId -> {
                    TeamManage teamManage = teamModuleService.findById(teamManageId, TeamManage.class);
                    TeamCalendar newTeamCalendar = TeamCalendar.builder()
                            .isAlarmed(false)
                            .build();
                    newTeamCalendar.setCalendar(newCalendar);
                    newTeamCalendar.setTeamManage(teamManage);
                    calendarModuleService.save(newTeamCalendar, TeamCalendar.class);
                });
    }

    @Override
    public void update(UpdateCalendar request, Long calendarId) {
        Calendar calendar = calendarModuleService.findById(calendarId, Calendar.class);
        calendar.updateTitle(request.title());
        calendar.updateContent(request.content());

        calendarModuleService.deleteAllTeamCalendarByCalendarId(calendarId);

        request.participants()
                .forEach(newTeamManageId -> {
                    TeamManage teamManage = teamModuleService.findById(newTeamManageId, TeamManage.class);
                    TeamCalendar newTeamCalendar = TeamCalendar.builder().isAlarmed(false).build();
                    newTeamCalendar.setCalendar(calendar);
                    newTeamCalendar.setTeamManage(teamManage);
                    calendarModuleService.save(newTeamCalendar, TeamCalendar.class);
                });

    }

    @Override
    public void updateState(Long calendarId) {
        calendarModuleService.findById(calendarId, Calendar.class).switchStatus();
    }

    @Override
    public void delete(Long calendarId) {
        Calendar calendar = calendarModuleService.findById(calendarId, Calendar.class);
        calendarModuleService.deleteAllTeamCalendarByCalendarId(calendarId);
        calendarModuleService.delete(calendar, Calendar.class);
    }


}
