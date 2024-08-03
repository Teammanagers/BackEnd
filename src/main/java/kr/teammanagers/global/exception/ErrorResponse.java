package kr.teammanagers.global.exception;

public record ErrorResponse(
        ErrorCode errorCode,
        String message
) {

}