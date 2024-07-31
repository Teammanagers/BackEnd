package kr.teammanagers.tag.application;

import kr.teammanagers.member.domain.Member;
import kr.teammanagers.memo.domain.Memo;
import kr.teammanagers.tag.domain.*;
import kr.teammanagers.tag.repository.*;
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
    private final TagMemoRepository tagMemoRepository;

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

    public void saveConfidentRole(final Tag tag, final Member member) {
        confidentRoleRepository.save(
                ConfidentRole.builder()
                        .member(member)
                        .tag(tag)
                        .build()
        );
    }

    public void saveTagMemo(final Tag tag, final Memo memo) {
        tagMemoRepository.save(
                TagMemo.builder()
                        .tag(tag)
                        .memo(memo)
                        .build()
        );
    }

    public Tag findOrCreateTag(final String tagName) {
        return tagRepository.findByName(tagName).orElseGet(() ->
                tagRepository.save(
                        Tag.builder()
                                .name(tagName)
                                .build()
                )
        );
    }

    public void validateAndDeleteTagByTagId(final Long tagId) {
        if (!isTagInUse(tagId)) {
            tagRepository.deleteById(tagId);
        }
    }

    private boolean isTagInUse(Long tagId) {
        return tagTeamRepository.existsByTagId(tagId) ||
                confidentRoleRepository.existsByTagId(tagId) ||
                tagMemoRepository.existsByTagId(tagId) ||
                teamRoleRepository.existsByTagId(tagId);
    }

    public void addNewConfidentRoles(final List<String> requestedRoles, final List<String> currentRoleNames, final Member member) {
        requestedRoles.stream()
                .filter(role -> !currentRoleNames.contains(role))
                .forEach(tagName -> {
                    Tag tag = findOrCreateTag(tagName);
                    saveConfidentRole(tag, member);
                });
    }

    public void addNewTagMemo(final List<String> requestedTagMemo, final List<String> currentTagMemoNames, final Memo memo) {
        requestedTagMemo.stream()
                .filter(role -> !currentTagMemoNames.contains(role))
                .forEach(tagName -> {
                    Tag tag = findOrCreateTag(tagName);
                    saveTagMemo(tag, memo);
                });
    }

    public void removeOldConfidentRoles(final List<String> requestedRoles, final List<ConfidentRole> currentRoles) {
        currentRoles.stream()
                .filter(role -> !requestedRoles.contains(role.getTag().getName()))
                .forEach(confidentRole -> {
                    Long tagId = confidentRole.getTag().getId();
                    confidentRoleRepository.delete(confidentRole);
                    validateAndDeleteTagByTagId(tagId);
                });
    }

    public void removeOldTagMemo(final List<String> requestedTagMemo, final List<TagMemo> currentTagMemoNames) {
        currentTagMemoNames.stream()
                .filter(role -> !requestedTagMemo.contains(role.getTag().getName()))
                .forEach(tagMemo -> {
                    Long tagId = tagMemo.getTag().getId();
                    tagMemoRepository.delete(tagMemo);
                    validateAndDeleteTagByTagId(tagId);
                });
    }
}
