package kr.teammanagers.tag.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.team.domain.Team;
import lombok.*;

@Entity
@Table(name = "team_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TagTeam extends AuditingField {

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
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    private TagTeam(final Tag tag, final Team team) {
        this.tag = tag;
        this.team = team;
    }
}
