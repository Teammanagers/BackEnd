package kr.teammanagers.calendar.repository;

import kr.teammanagers.calendar.domain.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
