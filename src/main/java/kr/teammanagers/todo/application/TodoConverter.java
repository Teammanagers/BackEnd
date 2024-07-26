package kr.teammanagers.todo.application;

import kr.teammanagers.todo.domain.Todo;
import kr.teammanagers.todo.dto.TodoDto;

public class TodoConverter {
    public static TodoDto toTodoDto(Todo todo) {
        return TodoDto.builder()
                .todoId(todo.getId())
                .title(todo.getTitle())
                .status(todo.getStatus())
                .build();
    }


}
