package kr.teammanagers.term.repository;

import kr.teammanagers.term.domain.CheckedTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<CheckedTerms, Long>, TermQueryDsl {
}
