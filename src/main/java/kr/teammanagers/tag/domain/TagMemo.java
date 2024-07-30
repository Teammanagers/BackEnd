package kr.teammanagers.tag.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.memo.domain.Memo;
import lombok.*;

@Entity
@Table(name = "memo_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TagMemo extends AuditingField {

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
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @Builder
    private TagMemo(final Tag tag, final Memo memo) {
        this.tag = tag;
        this.memo = memo;
    }
}
