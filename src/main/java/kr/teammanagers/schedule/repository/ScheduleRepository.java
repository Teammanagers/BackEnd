package kr.teammanagers.schedule.repository;

import kr.teammanagers.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Optional<Schedule> findByTeamManageId(Long teamManageId);
    void deleteByTeamManageId(Long teamManageId);
}
