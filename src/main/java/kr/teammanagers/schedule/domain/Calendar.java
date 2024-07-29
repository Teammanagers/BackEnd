package kr.teammanagers.schedule.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.common.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "calendar")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Calendar extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    private String content;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = false)
    private Status status;

    @Builder
    private Calendar(final String title, final String content, final LocalDateTime date, final Status status) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.status = status;
    }
}
