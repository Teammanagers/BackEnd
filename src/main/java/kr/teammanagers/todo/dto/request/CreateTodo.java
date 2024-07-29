package kr.teammanagers.todo.dto.request;

import kr.teammanagers.common.Status;
import kr.teammanagers.todo.domain.Todo;

public record CreateTodo(
        String title
) {
    public static Todo from(CreateTodo createTodo) {
        return Todo.builder()
                .title(createTodo.title())
                .status(Status.PROCEEDING)
                .build();
    }
}
