package kr.teammanagers.alarm.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.calendar.domain.TeamCalendar;
import kr.teammanagers.todo.domain.Todo;
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

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_calendar_id")
    private TeamCalendar teamCalendar;

    @Builder
    private Alarm(final String title, final String content, final LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }
}
