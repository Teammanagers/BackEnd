package kr.teammanagers.calendar.dto.request;

import kr.teammanagers.calendar.domain.Calendar;
import kr.teammanagers.common.Status;

import java.time.LocalDateTime;
import java.util.List;

public record CreateCalendar(
        LocalDateTime date,
        String title,
        List<Long> participants,
        String content
) {
    public Calendar toCalendar() {
        return Calendar.builder()
                .date(this.date)
                .title(this.title)
                .content(this.content)
                .status(Status.PROCEEDING)
                .build();   //Todo: Calendar에 participants 추가 필요
    }
}
