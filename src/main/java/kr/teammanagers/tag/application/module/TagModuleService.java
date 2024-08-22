package kr.teammanagers.tag.application.module;

import kr.teammanagers.member.domain.Member;
import kr.teammanagers.memo.domain.Memo;
import kr.teammanagers.tag.domain.ConfidentRole;
import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.domain.TagMemo;

import java.util.List;

public interface TagModuleService {
    TagMemo saveTagMemo(TagMemo tagMemo);

    List<TagMemo> findAllTagMemoByMemoId(Long memoId);

    void deleteTagMemo(TagMemo tagMemo);

    Tag findOrCreateTag(String tagName);

    void validateAndDeleteTagByTagId(Long tagId);

    void addNewConfidentRoles(List<String> requestedRoles, List<String> currentRoleNames, Member member);

    void addNewTagMemo(List<String> requestedTagMemo, List<String> currentTagMemoNames, Memo memo);

    void removeOldConfidentRoles(List<String> requestedRoles, List<ConfidentRole> currentRoles);

    void removeOldTagMemo(List<String> requestedTagMemo, List<TagMemo> currentTagMemoNames);

    List<Tag> getAllConfidentRole(Long authId);

    List<Tag> getAllTeamTag(Long teamId);

    List<Tag> getAllTeamRoleTag(Long teamManageId);
}
