package kr.teammanagers.team.repository;

import kr.teammanagers.tag.domain.TeamRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRoleRepository extends JpaRepository<TeamRole, Long> {

    List<TeamRole> findAllByTeamManageId(Long teamManageId);
}
