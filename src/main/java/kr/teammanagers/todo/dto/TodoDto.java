package kr.teammanagers.todo.dto;

import kr.teammanagers.common.Status;
import kr.teammanagers.todo.domain.Todo;
import lombok.Builder;
import lombok.Getter;

@Builder
public record TodoDto (
        Long todoId,
        String title,
        Status status
) {
    public static TodoDto from(Todo todo) {
        return TodoDto.builder()
                .todoId(todo.getId())
                .title(todo.getTitle())
                .status(todo.getStatus())
                .build();
    }
}

