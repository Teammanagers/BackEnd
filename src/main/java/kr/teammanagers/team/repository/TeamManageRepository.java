package kr.teammanagers.team.repository;

import kr.teammanagers.team.domain.TeamManage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamManageRepository extends JpaRepository<TeamManage, Long> {
}
