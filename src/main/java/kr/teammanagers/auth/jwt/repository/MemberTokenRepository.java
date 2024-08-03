package kr.teammanagers.auth.jwt.repository;

import kr.teammanagers.auth.jwt.domain.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberTokenRepository extends JpaRepository<MemberToken, Long> {
    Optional<MemberToken> findByAccessToken(String accessToken);
    Optional<MemberToken> findByRefreshToken(String refreshToken);
}
