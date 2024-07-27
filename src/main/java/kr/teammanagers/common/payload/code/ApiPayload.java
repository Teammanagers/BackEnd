package kr.teammanagers.common.payload.code;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kr.teammanagers.common.payload.code.base.BaseCode;
import kr.teammanagers.common.payload.code.status.SuccessStatus;

@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public record ApiPayload<T>(
        @JsonProperty("isSuccess")
        Boolean isSuccess,
        String code,
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        T result
) {

    // Success
    public static <T> ApiPayload<T> onSuccess(final T result) {
        return new ApiPayload<>(
                true,
                SuccessStatus._OK.getCode(),
                SuccessStatus._OK.getMessage(),
                result
        );
    }

    // Success : No result
    public static <T> ApiPayload<T> onSuccess() {
        return new ApiPayload<>(
                true,
                SuccessStatus._OK.getCode(),
                SuccessStatus._OK.getMessage(),
                null
        );
    }

    public static <T> ApiPayload<T> of(final BaseCode code, final T result) {
        return new ApiPayload<>(
                true,
                SuccessStatus._OK.getCode(),
                SuccessStatus._OK.getMessage(),
                result
        );
    }

    // Error
    public static <T> ApiPayload<T> onFailure(final String code, final String message, final T data) {
        return new ApiPayload<>(
                false,
                code,
                message,
                data
        );
    }
}
