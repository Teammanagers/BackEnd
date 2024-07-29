package kr.teammanagers.tag.application;

import kr.teammanagers.member.domain.Member;
import kr.teammanagers.tag.domain.ConfidentRole;
import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.domain.TagTeam;
import kr.teammanagers.tag.domain.TeamRole;
import kr.teammanagers.tag.repository.ConfidentRoleRepository;
import kr.teammanagers.tag.repository.TagRepository;
import kr.teammanagers.tag.repository.TagTeamRepository;
import kr.teammanagers.team.repository.TeamRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagModuleService {

    private final TagRepository tagRepository;
    private final ConfidentRoleRepository confidentRoleRepository;
    private final TagTeamRepository tagTeamRepository;
    private final TeamRoleRepository teamRoleRepository;

    public List<Tag> getAllConfidentRole(final Long authId) {
        return confidentRoleRepository.findAllByMemberId(authId).stream()
                .map(ConfidentRole::getTag)
                .toList();
    }

    public List<Tag> getAllTeamTag(final Long teamId) {
        return tagTeamRepository.findAllByTeamId(teamId).stream()
                .map(TagTeam::getTag)
                .toList();
    }

    public List<Tag> getAllTeamRoleTag(final Long teamManageId) {
        return teamRoleRepository.findAllByTeamManageId(teamManageId).stream()
                .map(TeamRole::getTag)
                .toList();
    }

    public void createConfidentRole(final String tagName, final Member member) {
        tagRepository.findByName(tagName).ifPresentOrElse(tag -> {
                    confidentRoleRepository.save(ConfidentRole.builder()
                            .member(member)
                            .tag(tag)
                            .build()
                    );
                },
                () -> {
                    Tag tag = tagRepository.save(Tag.builder()
                            .name(tagName)
                            .build());
                    confidentRoleRepository.save(ConfidentRole.builder()
                            .member(member)
                            .tag(tag)
                            .build()
                    );
                });
    }

    public void deleteConfidentRole(final ConfidentRole confidentRole) {
        Long tagId = confidentRole.getTag().getId();
        confidentRoleRepository.delete(confidentRole);
        if (!confidentRoleRepository.existsByTagId(tagId)) {
            tagRepository.deleteById(tagId);
        }
    }
}
