package kr.teammanagers.alarm.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.schedule.domain.TeamSchedule;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "alarm")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Alarm extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_schedule_id")
    private TeamSchedule teamSchedule;

    @Builder
    private Alarm(final String title, final String content, final LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
