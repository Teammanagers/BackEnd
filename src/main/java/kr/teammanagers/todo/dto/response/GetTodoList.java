package kr.teammanagers.todo.dto.response;

import kr.teammanagers.todo.dto.TodoListDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record GetTodoList(
        Long ownerTeamManageId,
        List<TodoListDto> teamTodoList,
        Integer pending,
        Integer proceeding,
        Integer completed
) {
    public static GetTodoList of(Long ownerTeamManageId, List<TodoListDto> teamTodoList, Integer pending, Integer proceeding, Integer completed) {
        return GetTodoList.builder()
                .ownerTeamManageId(ownerTeamManageId)
                .teamTodoList(teamTodoList)
                .pending(pending)
                .proceeding(proceeding)
                .completed(completed)
                .build();
    }
}
