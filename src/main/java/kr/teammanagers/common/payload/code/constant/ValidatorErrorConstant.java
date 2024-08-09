package kr.teammanagers.common.payload.code.constant;

public final class ValidatorErrorConstant {

    private ValidatorErrorConstant() {}
    public static final String NOT_NULL = "해당 필드는 null일 수 없습니다.";
    public static final String SIZE = "해당 필드의 최소 크기는 {min}, 최대 크기는 {max}입니다.";
}
