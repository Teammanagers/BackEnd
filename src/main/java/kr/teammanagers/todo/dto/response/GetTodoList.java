package kr.teammanagers.todo.dto.response;

import kr.teammanagers.todo.dto.TodoListDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record GetTodoList(
        List<TodoListDto> teamTodoList,
        Integer progress
) {
    public static GetTodoList of(List<TodoListDto> teamTodoList, Integer progress) {
        return GetTodoList.builder()
                .teamTodoList(teamTodoList)
                .progress(progress)
                .build();
    }
}
