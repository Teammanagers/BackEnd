package kr.teammanagers.team.repository;

import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.domain.TeamManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamManageRepository extends JpaRepository<TeamManage, Long> {
    List<TeamManage> findAllByTeamId(Long teamId);
}
