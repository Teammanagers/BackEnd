package kr.teammanagers.tag.repository;

import kr.teammanagers.tag.domain.TagTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagTeamRepository extends JpaRepository<TagTeam, Long> {

    List<TagTeam> findAllByTeamId(Long teamId);
}
