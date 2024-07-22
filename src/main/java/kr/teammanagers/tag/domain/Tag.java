package kr.teammanagers.tag.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Builder
    private Tag(final String name) {
        this.name = name;
    }
}
