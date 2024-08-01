package kr.teammanagers.team.dto;

import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.dto.TagDto;
import kr.teammanagers.team.domain.TeamManage;
import lombok.Builder;

import java.util.List;

@Builder
public record TeamMemberDto(
        Long teamManageId,
        String name,
        String imageUrl,
        List<TagDto> roleList
) {
    public static TeamMemberDto of(final TeamManage teamManage, final List<Tag> tagList) {
        return TeamMemberDto.builder()
                .teamManageId(teamManage.getId())
                .name(teamManage.getMember().getName())
                .imageUrl(teamManage.getMember().getImageUrl())
                .roleList(tagList.stream()
                        .map(TagDto::from)
                        .toList())
                .build();
    }
}
