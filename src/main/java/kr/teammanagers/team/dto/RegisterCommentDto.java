package kr.teammanagers.team.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import kr.teammanagers.common.payload.code.constant.ValidatorErrorConstant;

public record RegisterCommentDto(
        @NotNull(message = ValidatorErrorConstant.NOT_NULL)
        Long teamManageId,

        @NotNull(message = ValidatorErrorConstant.NOT_NULL)
        @Size(max = 50, message = ValidatorErrorConstant.SIZE)
        String content
) {
}
