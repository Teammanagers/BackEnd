package kr.teammanagers.todo.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TodoListDto {
    Long teamManageId;
    String name;
    //List<TagDto> roleTagList;
    List<TodoDto> todoList;
}
