package kr.teammanagers.todo.dto;

import kr.teammanagers.tag.dto.TagDto;
import kr.teammanagers.team.domain.TeamManage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record TodoListDto (
        Long teamManageId,
        String name,
        List<TagDto> roleTagList,
        List<TodoDto> todoList
){
    public static TodoListDto of(TeamManage teamManage, List<TagDto> roleTagList, List<TodoDto> todoList) {
        return TodoListDto.builder()
                .teamManageId(teamManage.getId())
                .name(teamManage.getMember().getName())
                .roleTagList(roleTagList)
                .todoList(todoList)
                .build();
    }
}
