package kr.teammanagers.todo.dto;

import kr.teammanagers.common.Status;
import lombok.Builder;

@Builder
public class TodoDto {
    Long todoId;
    String title;
    Status status;
}
