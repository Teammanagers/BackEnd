package kr.teammanagers.calendar.repository;

import kr.teammanagers.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByTeamId(Long teamId);
}
