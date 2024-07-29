package kr.teammanagers.term.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.member.domain.Member;
import kr.teammanagers.term.dto.CreateTerms;
import lombok.*;

@Entity
@Table(name = "checked_terms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckedTerms extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "terms_of_use", nullable = false)
    private Boolean termsOfUse;

    @Column(name = "privacy_policy", nullable = false)
    private Boolean privacyPolicy;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private CheckedTerms(final Boolean termsOfUse, final Boolean privacyPolicy) {
        this.termsOfUse = termsOfUse;
        this.privacyPolicy = privacyPolicy;
    }

    public static CheckedTerms from(final CreateTerms createTerms) {
        return CheckedTerms.builder()
                .privacyPolicy(createTerms.getPrivacyPolicy())
                .termsOfUse(createTerms.getTermsOfUse())
                .build();
    }
}
