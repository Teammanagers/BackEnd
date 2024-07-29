package kr.teammanagers.todo.dto.response;

import kr.teammanagers.todo.dto.TodoListDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetTodoList {
    List<TodoListDto> teamTodoList;
    Integer progress;
}
