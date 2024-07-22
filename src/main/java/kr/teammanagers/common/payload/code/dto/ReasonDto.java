package kr.teammanagers.common.payload.code.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ReasonDto(
        HttpStatus httpStatus,
        boolean isSuccess,
        String code,
        String message
) {
}
