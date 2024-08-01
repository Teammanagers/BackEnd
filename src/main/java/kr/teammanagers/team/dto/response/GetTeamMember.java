package kr.teammanagers.team.dto.response;

import kr.teammanagers.team.dto.SimpleTeamMemberDto;
import lombok.Builder;

import java.util.List;

@Builder
public record GetTeamMember(
        List<SimpleTeamMemberDto> teamMember
) {
    public static GetTeamMember from(final List<SimpleTeamMemberDto> teamMember) {
        return GetTeamMember.builder()
                .teamMember(teamMember)
                .build();
    }
}
