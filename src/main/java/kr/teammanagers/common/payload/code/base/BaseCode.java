package kr.teammanagers.common.payload.code.base;

import kr.teammanagers.common.payload.code.dto.ReasonDto;

public interface BaseCode {
    ReasonDto getReason();

    ReasonDto getReasonHttpStatus();
}
