package kr.teammanagers.member.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import lombok.*;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String content;

    @Column(nullable = false)
    private Boolean isHidden;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    private Comment(final String content, final Boolean isHidden) {
        this.content = content;
        this.isHidden = isHidden;
    }

    public void updateIsHidden() {
        this.isHidden = !this.isHidden;
    }
}
