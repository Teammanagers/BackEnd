package kr.teammanagers.global.exception;

public class TokenException extends CustomException {

    public TokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}