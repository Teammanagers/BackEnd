package kr.teammanagers.team.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterCommentDto(
        @NotNull
        Long teamManageId,

        @NotNull
        @Size(max = 50)
        String content
) {
}
