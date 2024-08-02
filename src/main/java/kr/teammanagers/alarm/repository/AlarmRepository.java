package kr.teammanagers.alarm.repository;

import kr.teammanagers.alarm.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
