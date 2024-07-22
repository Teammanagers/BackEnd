package kr.teammanagers.storage.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.team.domain.TeamManage;
import lombok.*;

@Entity
@Table(name = "team_data")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TeamData extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 20)
    private String size;

    @Column(nullable = false)
    private String fileUrl;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_manage_id")
    private TeamManage teamManage;

    @Builder
    private TeamData(final String title, final String size, final String fileUrl) {
        this.title = title;
        this.size = size;
        this.fileUrl = fileUrl;
    }
}
