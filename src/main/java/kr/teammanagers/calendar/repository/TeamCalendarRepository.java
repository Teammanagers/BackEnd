package kr.teammanagers.calendar.repository;

import kr.teammanagers.calendar.domain.TeamCalendar;
import kr.teammanagers.team.domain.TeamManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamCalendarRepository extends JpaRepository<TeamCalendar, Long>, TeamCalendarQueryDsl {
    List<TeamCalendar> findAllByTeamManage(TeamManage teamManage);
}
