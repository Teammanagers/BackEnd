package kr.teammanagers.todo.dto;

import kr.teammanagers.common.Status;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TodoDto {
    Long todoId;
    String title;
    Status status;
}
