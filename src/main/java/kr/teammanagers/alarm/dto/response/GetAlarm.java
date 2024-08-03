package kr.teammanagers.alarm.dto.response;

import kr.teammanagers.alarm.dto.AlarmDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetAlarm(
        List<AlarmDto> alarmList
) {
    public static GetAlarm from(List<AlarmDto> alarmList) {
        return GetAlarm.builder()
                .alarmList(alarmList)
                .build();
    }
}
