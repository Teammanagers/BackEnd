package kr.teammanagers.todo.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.common.Status;
import kr.teammanagers.team.domain.TeamManage;
import lombok.*;

@Entity
@Table(name = "todo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Todo extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Setter
    private String imageUrl;

    // Mapping
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_manage_id")
    private TeamManage teamManage;

    @Builder
    private Todo(final String title, final Status status) {
        this.title = title;
        this.status = status;
    }

    public void changeTitle(final String title) {
        this.title = title;
    }

    public void changeStatus(final int option) {
        switch (option) {
            case 1:
                this.status = Status.PENDING;
                break;
            case 2:
                this.status = Status.PROCEEDING;
                break;
            case 3:
                this.status = Status.COMPLETED;
                break;
        }
        if (this.status == Status.PROCEEDING) this.status = Status.COMPLETED;
        else this.status = Status.PROCEEDING;
    }

}
