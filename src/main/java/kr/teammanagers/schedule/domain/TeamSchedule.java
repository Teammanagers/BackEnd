package kr.teammanagers.schedule.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.team.domain.TeamManage;
import lombok.*;

@Entity
@Table(name = "team_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TeamSchedule extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean isAlarmed;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_mange_id")
    private TeamManage teamManage;

    @Builder
    private TeamSchedule(final Boolean isAlarmed) {
        this.isAlarmed = isAlarmed;
    }
}
