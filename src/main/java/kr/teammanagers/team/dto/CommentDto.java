package kr.teammanagers.team.dto;

import lombok.Builder;

@Builder
public record CommentDto(
        Long teamManageId,
        String content,
        Boolean isHidden
) {
}
