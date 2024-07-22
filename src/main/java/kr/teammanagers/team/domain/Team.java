package kr.teammanagers.team.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import kr.teammanagers.common.Status;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(name = "team_image_url")
    private String teamImageUrl;

    @Column(name = "team_code", nullable = false, length = 8)
    private String teamCode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    private Team(final String title, final String teamImageUrl, final String teamCode, final Status status) {
        this.title = title;
        this.teamImageUrl = teamImageUrl;
        this.teamCode = teamCode;
        this.status = status;
    }
}
