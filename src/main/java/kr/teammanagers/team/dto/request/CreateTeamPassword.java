package kr.teammanagers.team.dto.request;

import jakarta.validation.constraints.Size;
import kr.teammanagers.common.payload.code.constant.ValidatorErrorConstant;

public record CreateTeamPassword(
        @Size(max = 6, message = ValidatorErrorConstant.SIZE)
        String password
) {
}
