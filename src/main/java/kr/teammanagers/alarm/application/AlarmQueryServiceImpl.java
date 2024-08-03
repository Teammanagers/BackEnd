package kr.teammanagers.alarm.application;

import kr.teammanagers.alarm.domain.Alarm;
import kr.teammanagers.alarm.dto.AlarmDto;
import kr.teammanagers.alarm.dto.response.GetAlarm;
import kr.teammanagers.alarm.repository.AlarmRepository;
import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.global.exception.GeneralException;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmQueryServiceImpl implements AlarmQueryService {

    private final AlarmRepository alarmRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public GetAlarm get(Long memberId, Long teamId) {

        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_MANAGE_NOT_FOUND));

        List<AlarmDto> alarmDtoList = alarmRepository.findAllByTeamManageId(teamManage.getId()).stream()
                .map(AlarmDto::from)
                .toList();

        return GetAlarm.from(alarmDtoList);
    }
}
