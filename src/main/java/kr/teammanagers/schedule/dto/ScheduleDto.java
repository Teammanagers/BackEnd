package kr.teammanagers.schedule.dto;

import kr.teammanagers.schedule.domain.TimeTable;

public record ScheduleDto(
        TimeTable monday,
        TimeTable tuesday,
        TimeTable wednesday,
        TimeTable thursday,
        TimeTable friday,
        TimeTable saturday,
        TimeTable sunday
) {
}
