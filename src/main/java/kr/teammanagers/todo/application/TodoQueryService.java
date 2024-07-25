package kr.teammanagers.todo.application;

import kr.teammanagers.todo.dto.request.CreateTodo;

public interface TodoQueryService {
    public void createTodo(CreateTodo request);
}
