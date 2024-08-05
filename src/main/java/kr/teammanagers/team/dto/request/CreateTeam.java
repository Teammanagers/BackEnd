package kr.teammanagers.team.dto.request;

import jakarta.validation.constraints.NotNull;
import kr.teammanagers.common.Status;
import kr.teammanagers.tag.exception.TagMaxSize;
import kr.teammanagers.team.constant.TeamConstant;
import kr.teammanagers.team.domain.Team;

import java.util.List;

public record CreateTeam(
        @NotNull
        String title,

        @TagMaxSize
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
