package kr.teammanagers.team.dto.request;

import kr.teammanagers.common.Status;
import kr.teammanagers.team.constant.TeamConstant;
import kr.teammanagers.team.domain.Team;

import java.util.List;

public record CreateTeam(
        String title,
        List<String> teamTagList
) {
    public Team toTeam(final String imageUrl) {
        return Team.builder()
                .title(title)
                .teamImageUrl(imageUrl)
                .status(Status.PROCEEDING)
                .password(TeamConstant.DEFAULT_TEAM_PASSWORD)
                .build();
    }
}
