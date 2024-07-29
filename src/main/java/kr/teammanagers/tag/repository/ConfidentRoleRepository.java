package kr.teammanagers.tag.repository;

import kr.teammanagers.tag.domain.ConfidentRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfidentRoleRepository extends JpaRepository<ConfidentRole, Long> {

    List<ConfidentRole> findAllByMemberId(Long memberId);

    boolean existsByTagId(Long tagId);
}
