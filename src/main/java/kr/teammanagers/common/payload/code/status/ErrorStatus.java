package kr.teammanagers.common.payload.code.status;

import kr.teammanagers.common.payload.code.base.ErrorBaseCode;
import kr.teammanagers.common.payload.code.dto.ErrorReasonDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements ErrorBaseCode {

    // 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "403", "금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND, "404", "찾을 수 없습니다."),

    // 팀 관련 응답
    TEAM_NOT_FOUND(HttpStatus.NOT_FOUND, "TEAM4001", "존재하지 않는 팀입니다."),

    // 팀관리 관련 응답
    TEAM_MANAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "TEAMMANAGE4001", "존재하지 않는 팀관리입니다."),

    // 투두 관련 응답
    TODO_NOT_FOUND(HttpStatus.NOT_FOUND, "TODO4001", "존재하지 않는 투두입니다."),

    // 멤버 관련 응답
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "존재하지 않는 유저입니다."),

    // 일정 관련 응답
    CALENDAR_NOT_FOUND(HttpStatus.NOT_FOUND, "CALENDAR4001", "존재하지 않는 일정입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return null;
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return null;
    }
}
