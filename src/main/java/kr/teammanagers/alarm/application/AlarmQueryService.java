package kr.teammanagers.alarm.application;

import kr.teammanagers.alarm.dto.response.GetAlarm;

public interface AlarmQueryService {
    GetAlarm get(Long memberId, Long teamId);
}
