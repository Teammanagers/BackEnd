package kr.teammanagers.team.dto.response;

import kr.teammanagers.tag.domain.Tag;
import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.dto.TeamDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetTeam(
        TeamDto team
) {
    public static GetTeam from(final Team team, final List<Tag> tagList) {
        return GetTeam.builder()
                .team(TeamDto.from(team, tagList))
                .build();
    }
}
