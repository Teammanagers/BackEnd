package kr.teammanagers.schedule.dto.request;

import kr.teammanagers.schedule.domain.Schedule;
import kr.teammanagers.schedule.domain.TimeTable;

public record CreateSchedule(
        TimeTable monday,
        TimeTable tuesday,
        TimeTable wednesday,
        TimeTable thursday,
        TimeTable friday,
        TimeTable saturday,
        TimeTable sunday
) {
    public Schedule toSchedule() {
        return Schedule.builder()
                .monday(this.monday)
                .tuesday(this.tuesday)
                .wednesday(this.wednesday)
                .thursday(this.thursday)
                .friday(this.friday)
                .saturday(this.saturday)
                .sunday(this.sunday)
                .build();
    }
}
