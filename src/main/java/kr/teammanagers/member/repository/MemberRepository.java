package kr.teammanagers.member.repository;

import kr.teammanagers.member.domain.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
    Optional<Member> findByProviderId(String providerId);
}
