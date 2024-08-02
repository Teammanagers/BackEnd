package kr.teammanagers.schedule.application;

import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.global.exception.GeneralException;
import kr.teammanagers.schedule.domain.Schedule;
import kr.teammanagers.schedule.dto.request.CreateSchedule;
import kr.teammanagers.schedule.dto.request.UpdateSchedule;
import kr.teammanagers.schedule.repository.ScheduleRepository;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleCommandServiceImpl implements ScheduleCommandService{

    private final ScheduleRepository scheduleRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public void create(Long memberId, Long teamId, CreateSchedule request) {
        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_MANAGE_NOT_FOUND));

        Schedule newSchedule = request.toSchedule();
        newSchedule.setTeamManage(teamManage);

        scheduleRepository.save(newSchedule);
    }

    @Override
    public void update(Long memberId, Long teamId, UpdateSchedule request) {
        TeamManage teamManage = teamManageRepository.findByMemberIdAndTeamId(memberId, teamId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_MANAGE_NOT_FOUND));

        Schedule scheduleForUpdate = scheduleRepository.findByTeamManageId(teamManage.getId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.SCHEDULE_NOT_FOUND));

        scheduleForUpdate.update(
                request.monday(),
                request.tuesday(),
                request.wednesday(),
                request.thursday(),
                request.friday(),
                request.saturday(),
                request.sunday()
        );
    }
}
