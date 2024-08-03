package kr.teammanagers.schedule.application;

import kr.teammanagers.schedule.dto.response.GetTeamSchedule;

public interface ScheduleQueryService {
    GetTeamSchedule getTeamSchedule(long teamId);
}
