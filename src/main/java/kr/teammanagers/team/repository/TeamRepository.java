package kr.teammanagers.team.repository;

import kr.teammanagers.team.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamCode(final String teamCode);
}
