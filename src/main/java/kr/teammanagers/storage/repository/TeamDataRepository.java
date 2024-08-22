package kr.teammanagers.storage.repository;

import kr.teammanagers.storage.domain.TeamData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamDataRepository extends JpaRepository<TeamData, Long> {

    List<TeamData> findAllByTeamManageId(Long teamManageId);
}
