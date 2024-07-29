package kr.teammanagers.auth.domain.redis.repository;

import java.util.Optional;

import kr.teammanagers.auth.domain.redis.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

    Optional<Token> findByAccessToken(String accessToken);
}
