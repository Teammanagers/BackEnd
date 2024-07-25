package kr.teammanagers.todo.application;

import kr.teammanagers.todo.dto.request.CreateTodo;

public interface TodoCommandService {
    public void createTodo(CreateTodo request, Long teamManageId);
}
