package kr.teammanagers.schedule.dto;

import kr.teammanagers.schedule.domain.TimeTable;
import lombok.Builder;

@Builder
public record ScheduleDto(
        TimeTable monday,
        TimeTable tuesday,
        TimeTable wednesday,
        TimeTable thursday,
        TimeTable friday,
        TimeTable saturday,
        TimeTable sunday
) {
    public static ScheduleDto of(TimeTable monday,
                                 TimeTable tuesday,
                                 TimeTable wednesday,
                                 TimeTable thursday,
                                 TimeTable friday,
                                 TimeTable saturday,
                                 TimeTable sunday
                                   ) {
        return ScheduleDto.builder()
                .monday(monday)
                .tuesday(tuesday)
                .wednesday(wednesday)
                .thursday(thursday)
                .friday(friday)
                .saturday(saturday)
                .sunday(sunday)
                .build();
    }
}
