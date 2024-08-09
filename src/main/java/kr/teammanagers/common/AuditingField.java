package kr.teammanagers.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditingField {

    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Setter
    @Enumerated(value = EnumType.STRING)
    private EntityStatus entityStatus = EntityStatus.ACTIVE;

    public void updateStatus(final EntityStatus status){
        this.entityStatus = status;
    }

}
