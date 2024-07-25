package kr.teammanagers.todo.dto;

import java.util.List;

public class TodoListDto {
    Long teamManageId;
    String name;
    List<TagDto> roleTagList;
    List<TodoDto> todoList;
}
