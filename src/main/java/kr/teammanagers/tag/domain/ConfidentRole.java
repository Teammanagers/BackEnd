package kr.teammanagers.tag.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.member.domain.Member;
import lombok.*;

@Entity
@Table(name = "confident_role")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ConfidentRole extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private ConfidentRole(final Tag tag, final Member member) {
        this.tag = tag;
        this.member = member;
    }
}
