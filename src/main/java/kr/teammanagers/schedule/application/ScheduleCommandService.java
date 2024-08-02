package kr.teammanagers.schedule.application;

import kr.teammanagers.schedule.dto.request.CreateSchedule;

public interface ScheduleCommandService {
    void create(Long memberId, Long teamId, CreateSchedule request);
}
