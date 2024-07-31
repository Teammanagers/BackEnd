package kr.teammanagers.team.dto.response;

import kr.teammanagers.team.domain.Team;
import lombok.Builder;

@Builder
public record CreateTeamResult(
        Long teamId,
        String teamCode
) {
    public static CreateTeamResult from(final Team team) {
        return CreateTeamResult.builder()
                .teamId(team.getId())
                .teamCode(team.getTeamCode())
                .build();
    }
}
