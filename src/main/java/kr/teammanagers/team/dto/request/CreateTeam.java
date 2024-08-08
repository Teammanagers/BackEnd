package kr.teammanagers.team.dto.request;

import jakarta.validation.constraints.NotNull;
import kr.teammanagers.common.Status;
import kr.teammanagers.common.payload.code.constant.ValidatorErrorConstant;
import kr.teammanagers.tag.constant.TagErrorConstant;
import kr.teammanagers.tag.exception.validator.TagMaxSize;
import kr.teammanagers.team.constant.TeamConstant;
import kr.teammanagers.team.domain.Team;

import java.util.List;

public record CreateTeam(
        @NotNull(message = ValidatorErrorConstant.NOT_NULL)
        String title,

        @TagMaxSize(message = TagErrorConstant.TAG_MAX_SIZE_LIMIT)
        List<String> teamTagList
) {
    public Team toTeam() {
        return Team.builder()
                .title(title)
                .status(Status.PROCEEDING)
                .password(TeamConstant.DEFAULT_TEAM_PASSWORD)
                .build();
    }
}
