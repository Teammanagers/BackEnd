package kr.teammanagers.todo.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class TodoListDto {
    Long teamManageId;
    String name;
    //List<TagDto> roleTagList;
    List<TodoDto> todoList;
}
