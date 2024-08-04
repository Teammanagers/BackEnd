package kr.teammanagers.schedule.dto.request;

import kr.teammanagers.schedule.domain.TimeTable;

public record UpdateSchedule(
        TimeTable monday,
        TimeTable tuesday,
        TimeTable wednesday,
        TimeTable thursday,
        TimeTable friday,
        TimeTable saturday,
        TimeTable sunday
) {
}
