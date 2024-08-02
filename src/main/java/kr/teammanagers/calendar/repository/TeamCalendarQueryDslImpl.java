package kr.teammanagers.calendar.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teammanagers.calendar.domain.Calendar;
import static kr.teammanagers.calendar.domain.QCalendar.calendar;
import static kr.teammanagers.calendar.domain.QTeamCalendar.teamCalendar;
import static kr.teammanagers.team.domain.QTeam.team;
import static kr.teammanagers.team.domain.QTeamManage.teamManage;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TeamCalendarQueryDslImpl implements TeamCalendarQueryDsl {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Calendar> findAllCalendarByMemberIdAndTeamIdAndMonth(Long memberId, Long teamId, Integer month) {
        return queryFactory.select(teamCalendar.calendar)
                .from(teamCalendar)
                .join(teamCalendar.calendar, calendar)
                .join(teamCalendar.teamManage, teamManage)
                .join(teamManage.team, team)
                .where(team.id.eq(teamId)
                        .and(calendar.date.month().eq(month)))
                .distinct()
                .fetch();
    }
}
