package kr.teammanagers.common.payload.code.base;

import kr.teammanagers.common.payload.code.dto.ErrorReasonDto;

public interface ErrorBaseCode {
    ErrorReasonDto getReason();

    ErrorReasonDto getReasonHttpStatus();
}
