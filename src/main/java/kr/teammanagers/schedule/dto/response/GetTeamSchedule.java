package kr.teammanagers.schedule.dto.response;

import kr.teammanagers.schedule.dto.ScheduleDto;

import java.util.List;

public record GetTeamSchedule(
        List<Long> participants,
        ScheduleDto scheduleDto
) {
}
