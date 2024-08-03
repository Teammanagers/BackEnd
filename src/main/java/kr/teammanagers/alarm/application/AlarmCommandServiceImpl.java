package kr.teammanagers.alarm.application;

import kr.teammanagers.alarm.domain.Alarm;
import kr.teammanagers.alarm.domain.AlarmType;
import kr.teammanagers.alarm.dto.request.CreateAlarm;
import kr.teammanagers.alarm.repository.AlarmRepository;
import kr.teammanagers.calendar.repository.CalendarRepository;
import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.global.exception.GeneralException;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmCommandServiceImpl implements AlarmCommandService {

    private final AlarmRepository alarmRepository;
    private final TeamManageRepository teamManageRepository;
    private final CalendarRepository calendarRepository;

    @Override
    public void createAlarm(CreateAlarm request, Long memberId, Long teamId, Long referenceId) {

        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_MANAGE_NOT_FOUND));

        Alarm alarm = Alarm.builder()
                .type(request.alarmType())
                .referenceId(referenceId)
                .date(LocalDateTime.now())
                .isRead(false)
                .build();

        alarm.setTeamManage(teamManage);

        alarmRepository.save(alarm);
    }

    @Override
    public void delete(Long alarmId) {

        alarmRepository.deleteById(alarmId);

    }
}
