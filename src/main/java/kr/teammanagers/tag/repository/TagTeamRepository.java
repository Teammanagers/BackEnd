package kr.teammanagers.tag.repository;

import kr.teammanagers.tag.domain.TagTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagTeamRepository extends JpaRepository<TagTeam, Long> {

    List<TagTeam> findAllByTeamId(Long teamId);

    Optional<TagTeam> findByTeamIdAndTagId(Long teamId, Long tagId);

    Boolean existsByTagId(Long tagId);
}
