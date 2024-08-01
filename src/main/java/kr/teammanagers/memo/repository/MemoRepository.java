package kr.teammanagers.memo.repository;

import kr.teammanagers.memo.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findAllByTeamId(Long teamId);
}
