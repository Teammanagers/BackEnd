package kr.teammanagers.todo.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateTodo(
        @Size(max = 30)
        String title
) {
}
