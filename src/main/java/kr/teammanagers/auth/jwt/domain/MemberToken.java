package kr.teammanagers.auth.jwt.domain;

import jakarta.persistence.*;
import kr.teammanagers.member.domain.Member;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "member_token")
public class MemberToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(length = 512, nullable = false)
    private String accessToken;

    @Column(length = 512, nullable = false)
    private String refreshToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresAt;

    @Builder
    public MemberToken(Member member, String accessToken, String refreshToken, Date createdAt, Date expiresAt) {
        this.member = member;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
