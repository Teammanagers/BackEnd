package kr.teammanagers.todo.application;

import kr.teammanagers.todo.dto.request.CreateTodo;
import kr.teammanagers.todo.dto.request.UpdateTodo;

public interface TodoCommandService {
    public void createTodo(CreateTodo request, Long teamManageId);
    public void updateTodoTitle(UpdateTodo reqeust, Long todoId);
    public void updateTodoStatus(Long todoId);
    public void deleteTodo(Long todoId);
}
