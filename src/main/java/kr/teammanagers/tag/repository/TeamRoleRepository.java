package kr.teammanagers.tag.repository;

import kr.teammanagers.tag.domain.TeamRole;
import kr.teammanagers.team.domain.TeamManage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRoleRepository extends JpaRepository<TeamRole, Long> {

    List<TeamRole> findAllByTeamManageId(Long teamManageId);

    Optional<TeamRole> findByTeamManageIdAndTagId(Long teamManageId, Long tagId);

    Boolean existsByTagId(Long tagId);
}
