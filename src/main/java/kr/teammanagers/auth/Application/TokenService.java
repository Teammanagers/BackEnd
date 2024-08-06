package kr.teammanagers.auth.Application;

import kr.teammanagers.auth.jwt.domain.MemberToken;
import kr.teammanagers.auth.jwt.repository.MemberTokenRepository;
import kr.teammanagers.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import kr.teammanagers.member.repository.MemberRepository;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final MemberTokenRepository memberTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveOrUpdate(String ProviderId, String refreshToken, String accessToken) {
        Optional<MemberToken> existingToken = memberTokenRepository.findByAccessToken(accessToken);
        if (existingToken.isPresent()) {
            MemberToken token = existingToken.get();
            token.setRefreshToken(refreshToken);
            memberTokenRepository.save(token);
        } else {
            // Assuming you have a method to find Member by ID
            Member member = findByProviderId(ProviderId);

            MemberToken token = MemberToken.builder()
                    .member(member)
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .createdAt(new Date())
                    .expiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                    .build();
            memberTokenRepository.save(token);
        }
    }

/*    @Transactional
    public void deleteTokensByMember(Member member) {
        memberTokenRepository.deleteByMember(member);
    }*/

    @Transactional(readOnly = true)
    public Optional<MemberToken> getTokenByAccessToken(String accessToken) {
        return memberTokenRepository.findByAccessToken(accessToken);
    }

    @Transactional(readOnly = true)
    public Optional<MemberToken> getTokenByRefreshToken(String refreshToken) {
        return memberTokenRepository.findByRefreshToken(refreshToken);
    }

    private Member findByProviderId(String providerId) {
        return memberRepository.findByProviderId(String.valueOf(providerId))
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + providerId));
    }
}