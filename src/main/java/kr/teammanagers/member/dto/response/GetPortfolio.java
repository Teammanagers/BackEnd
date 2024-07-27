package kr.teammanagers.member.dto.response;

import kr.teammanagers.member.domain.Member;
import kr.teammanagers.storage.domain.TeamData;
import kr.teammanagers.storage.dto.StorageDto;
import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.team.domain.Team;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetPortfolio(
        Long teamId,
        String name,
        LocalDateTime start,
        LocalDateTime end,
        List<String> teamTagList,
        List<String> teamMemberList,
        List<String> teamMyRole,
        List<StorageDto> storageList
) {

    public static GetPortfolio from(final Team team, final List<Tag> teamTagList, final List<Member> memberList, final List<Tag> teamRoleList, final List<TeamData> storageList) {
        return GetPortfolio.builder()
                .teamId(team.getId())
                .name(team.getTitle())
                .start(team.getCreatedAt())
                .end(team.getUpdatedAt())
                .teamTagList(teamTagList.stream()
                        .map(Tag::getName)
                        .toList())
                .teamMemberList(memberList.stream()
                        .map(Member::getName)
                        .toList())
                .teamMyRole(teamRoleList.stream()
                        .map(Tag::getName)
                        .toList())
                .storageList(storageList.stream()
                        .map(StorageDto::from)
                        .toList())
                .build();
    }
}
