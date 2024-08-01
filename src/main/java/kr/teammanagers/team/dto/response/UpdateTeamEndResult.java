package kr.teammanagers.team.dto.response;

import kr.teammanagers.team.dto.TeamMemberDto;
import lombok.Builder;

import java.util.List;

@Builder
public record UpdateTeamEndResult(
        List<TeamMemberDto> teamMemberList
) {
    public static UpdateTeamEndResult from(List<TeamMemberDto> teamMemberList) {
        return UpdateTeamEndResult.builder()
                .teamMemberList(teamMemberList)
                .build();
    }
}
