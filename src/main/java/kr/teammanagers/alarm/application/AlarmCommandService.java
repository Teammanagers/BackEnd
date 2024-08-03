package kr.teammanagers.alarm.application;

import kr.teammanagers.alarm.domain.AlarmType;
import kr.teammanagers.alarm.dto.request.CreateAlarm;

public interface AlarmCommandService {
    void createAlarm(CreateAlarm request, Long memberId, Long teamId, Long referenceId);
    void delete(Long alarmId);
}
