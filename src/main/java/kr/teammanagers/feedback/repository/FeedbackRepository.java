package kr.teammanagers.feedback.repository;

import kr.teammanagers.feedback.domain.Feedback;
import kr.teammanagers.storage.domain.TeamData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByTeamDataId(Long teamDataId);
}
