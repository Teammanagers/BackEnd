package kr.teammanagers.alarm.dto;

import kr.teammanagers.alarm.domain.AlarmType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record AlarmDto(
        Long alarmId,
        AlarmType alarmType,
        Long referenceId,
        LocalDateTime date,
        Boolean isRead
) {

}
