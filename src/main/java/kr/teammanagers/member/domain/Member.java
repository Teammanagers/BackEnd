package kr.teammanagers.member.domain;

import jakarta.persistence.*;
import kr.teammanagers.common.AuditingField;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends AuditingField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(length = 20)
    private String belong;

    @Builder
    private Member(final String name, final LocalDate birth, final String email, final String imageUrl, final String phoneNumber, final String belong) {
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.imageUrl = imageUrl;
        this.phoneNumber = phoneNumber;
        this.belong = belong;
    }

    public void updateBelong(final String belong) {
        this.belong = belong;
    }

    public void updateImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
