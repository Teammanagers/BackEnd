package kr.teammanagers.tag.application.command;

import kr.teammanagers.tag.application.module.TagModuleService;
import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.domain.TagTeam;
import kr.teammanagers.tag.domain.TeamRole;
import kr.teammanagers.tag.request.CreateRoleTag;
import kr.teammanagers.tag.request.UpdateRoleTag;
import kr.teammanagers.tag.request.UpdateTeamTag;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagCommandServiceImpl implements TagCommandService {

    private final TagModuleService tagModuleService;
    private final TeamManageRepository teamManageRepository;

    @Override
    public void updateTeamTag(final Long teamId, final Long tagId, final UpdateTeamTag request) {
        TagTeam tagTeam = tagModuleService.findTagTeamByTeamIdAndTagId(teamId, tagId);
        Tag newTag = tagModuleService.findOrCreateTag(request.name());

        Long oldTagId = tagTeam.getTag().getId();
        tagTeam.setTag(newTag);
        tagModuleService.save(tagTeam, TagTeam.class);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }

    @Override
    public void deleteTeamTag(final Long teamId, final Long tagId) {
        TagTeam tagTeam = tagModuleService.findTagTeamByTeamIdAndTagId(teamId, tagId);
        Long oldTagId = tagTeam.getTag().getId();
        tagModuleService.delete(tagTeam, TagTeam.class);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }

    @Override
    public void createRoleTag(final Long teamManageId, final CreateRoleTag request) {
        TeamManage teamManage = teamManageRepository.findById(teamManageId)
                .orElseThrow(RuntimeException::new);// TODO : 예외 처리 필요

        Tag tag = tagModuleService.findOrCreateTag(request.name());
        tagModuleService.save(
                TeamRole.builder()
                        .teamManage(teamManage)
                        .tag(tag)
                        .build(),
                TeamRole.class
        );
    }

    @Override
    public void updateRoleTag(final Long teamManageId, final Long tagId, final UpdateRoleTag request) {
        TeamRole teamRole = tagModuleService.findByEntityIdAndTagId(teamManageId, tagId, TeamRole.class);
        Tag newTag = tagModuleService.findOrCreateTag(request.name());

        Long oldTagId = teamRole.getTag().getId();
        teamRole.setTag(newTag);
        tagModuleService.save(teamRole, TeamRole.class);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }

    @Override
    public void deleteRoleTag(final Long teamManageId, final Long tagId) {
        TeamRole teamRole = tagModuleService.findByEntityIdAndTagId(teamManageId, tagId, TeamRole.class);
        Long oldTagId = teamRole.getTag().getId();
        tagModuleService.delete(teamRole, TeamRole.class);
        tagModuleService.validateAndDeleteTagByTagId(oldTagId);
    }
}
