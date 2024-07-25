package kr.teammanagers.todo.dto.response;

import kr.teammanagers.todo.dto.TodoListDto;
import lombok.Builder;

import java.util.List;

@Builder
public class GetTodoList {
    List<TodoListDto> teamTodoList;
    Integer progress;
}
