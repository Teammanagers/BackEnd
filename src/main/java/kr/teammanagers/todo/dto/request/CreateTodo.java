package kr.teammanagers.todo.dto.request;

import kr.teammanagers.common.Status;
import kr.teammanagers.todo.domain.Todo;

public record CreateTodo(
        String title
) {
    public Todo toTodo() {
        return Todo.builder()
                .title(title)
                .status(Status.PROCEEDING)
                .build();
    }
}
