package kr.teammanagers.team.dto.request;

import kr.teammanagers.team.constant.TeamErrorConstant;
import kr.teammanagers.team.dto.RegisterCommentDto;
import kr.teammanagers.team.exception.validator.TeamMemberSize;

import java.util.List;

public record CreateTeamComment(
        @TeamMemberSize(message = TeamErrorConstant.TEAM_MEMBER_SIZE)
        List<RegisterCommentDto> commentList
) {
}
