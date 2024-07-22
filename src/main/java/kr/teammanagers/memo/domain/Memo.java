package kr.teammanagers.memo.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Memo extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @Builder
    private Memo(final String title, final String content) {
        this.title = title;
        this.content = content;
    }
}
