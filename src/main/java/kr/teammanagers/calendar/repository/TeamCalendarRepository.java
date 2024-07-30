package kr.teammanagers.calendar.repository;

import kr.teammanagers.calendar.domain.TeamCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamCalendarRepository extends JpaRepository<TeamCalendar, Long> {
}
