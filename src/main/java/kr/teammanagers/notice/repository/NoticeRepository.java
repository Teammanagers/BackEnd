package kr.teammanagers.notice.repository;

import kr.teammanagers.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByTeamId(Long teamId);
}
