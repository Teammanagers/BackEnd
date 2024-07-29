package kr.teammanagers.tag.application;

import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.domain.TagTeam;
import kr.teammanagers.tag.domain.TeamRole;
import kr.teammanagers.tag.repository.TagTeamRepository;
import kr.teammanagers.tag.repository.TeamRoleRepository;
import kr.teammanagers.tag.request.CreateRoleTag;
import kr.teammanagers.tag.request.UpdateRoleTag;
import kr.teammanagers.tag.request.UpdateTeamTag;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagService {

    private final TagTeamRepository tagTeamRepository;
    private final TagModuleService tagModuleService;
    private final TeamRoleRepository teamRoleRepository;
    private final TeamManageRepository teamManageRepository;

    @Transactional
    public void updateTeamTag(final Long teamId, final Long tagId, final UpdateTeamTag request) {
        TagTeam tagTeam = tagTeamRepository.findByTeamIdAndTagId(teamId, tagId)
                .orElseThrow(RuntimeException::new);

        Tag newTag = tagModuleService.findOrCreateTag(request.name());

        Long oldTagId = tagTeam.getTag().getId();
        tagTeam.setTag(newTag);
        tagTeamRepository.save(tagTeam);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }

    @Transactional
    public void deleteTeamTag(final Long teamId, final Long tagId) {
        TagTeam tagTeam = tagTeamRepository.findByTeamIdAndTagId(teamId, tagId)
                .orElseThrow(RuntimeException::new);            // TODO : 예외 처리 필요
        Long oldTagId = tagTeam.getTag().getId();
        tagTeamRepository.delete(tagTeam);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }

    @Transactional
    public void createRoleTag(final Long teamManageId, final CreateRoleTag request) {
        TeamManage teamManage = teamManageRepository.findById(teamManageId)
                .orElseThrow(RuntimeException::new);// TODO : 예외 처리 필요

        Tag tag = tagModuleService.findOrCreateTag(request.name());
        teamRoleRepository.save(
                TeamRole.builder()
                        .teamManage(teamManage)
                        .tag(tag)
                        .build()
        );
    }

    @Transactional
    public void updateRoleTag(final Long teamManageId, final Long tagId, final UpdateRoleTag request) {
        TeamRole teamRole = teamRoleRepository.findByTeamManageIdAndTagId(teamManageId, tagId)
                .orElseThrow(RuntimeException::new);// TODO : 예외 처리 필요

        Tag newTag = tagModuleService.findOrCreateTag(request.name());

        Long oldTagId = teamRole.getTag().getId();
        teamRole.setTag(newTag);
        teamRoleRepository.save(teamRole);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }

    @Transactional
    public void deleteRoleTag(final Long teamManageId, final Long tagId) {
        TeamRole teamRole = teamRoleRepository.findByTeamManageIdAndTagId(teamManageId, tagId)
                .orElseThrow(RuntimeException::new);            // TODO : 예외 처리 필요
        Long oldTagId = teamRole.getTag().getId();
        teamRoleRepository.delete(teamRole);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }
}
