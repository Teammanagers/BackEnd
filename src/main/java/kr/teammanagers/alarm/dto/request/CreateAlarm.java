package kr.teammanagers.alarm.dto.request;

import kr.teammanagers.alarm.domain.AlarmType;

public record CreateAlarm(
        AlarmType alarmType
) {
}
