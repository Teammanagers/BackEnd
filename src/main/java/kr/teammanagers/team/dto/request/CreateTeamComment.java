package kr.teammanagers.team.dto.request;

import kr.teammanagers.team.dto.RegisterCommentDto;

import java.util.List;

public record CreateTeamComment(
        List<RegisterCommentDto> commentList
) {
}
