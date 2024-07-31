package kr.teammanagers.team.dto;

import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.tag.dto.TagDto;
import kr.teammanagers.team.domain.Team;
import lombok.Builder;

import java.util.List;

@Builder
public record TeamDto(
        Long teamId,
        String title,
        String teamCode,
        String imageUrl,
        List<TagDto> teamTagList
) {

    public static TeamDto from(final Team team, final List<Tag> tagList) {
        return TeamDto.builder()
                .teamId(team.getId())
                .title(team.getTitle())
                .teamCode(team.getTeamCode())
                .imageUrl(team.getTeamImageUrl())
                .teamTagList(tagList.stream()
                        .map(TagDto::from)
                        .toList())
                .build();
    }
}
