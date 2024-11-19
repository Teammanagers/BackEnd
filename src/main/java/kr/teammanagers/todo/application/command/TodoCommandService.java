package kr.teammanagers.todo.application.command;

import kr.teammanagers.todo.dto.request.CreateTodo;
import kr.teammanagers.todo.dto.request.UpdateTodo;
import org.springframework.web.multipart.MultipartFile;

public interface TodoCommandService {
    void createTodo(CreateTodo request, Long teamManageId);
    void updateTodoTitle(UpdateTodo request, Long todoId);
    void updateTodoStatus(Long todoId, Integer statusNum);
    void deleteTodo(Long todoId);
    void uploadTodoImage(Long teamId, Long todoId, MultipartFile image);
}
