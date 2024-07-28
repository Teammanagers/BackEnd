package kr.teammanagers.todo.application;

import kr.teammanagers.common.Status;
import kr.teammanagers.common.payload.code.GeneralException;
import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.team.repository.TeamManageRepository;
import kr.teammanagers.todo.domain.Todo;
import kr.teammanagers.todo.dto.request.CreateTodo;
import kr.teammanagers.todo.dto.request.UpdateTodo;
import kr.teammanagers.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoCommandServiceImpl implements TodoCommandService {

    private final TodoRepository todoRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public void createTodo(CreateTodo request, Long teamManageId) {
        Todo newTodo = Todo.builder()
                .title(request.getTitle())
                .status(Status.PROCEEDING)
                .build();

        newTodo.setTeamManage(teamManageRepository.findById(teamManageId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TEAM_MANAGE_NOT_FOUND)));

        todoRepository.save(newTodo);
    }

    @Override
    public void updateTodoTitle(UpdateTodo request, Long todoId) {
        Todo todoForUpdate = todoRepository.findById(todoId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TODO_NOT_FOUND));

        todoForUpdate.changeTitle(request.getTitle());
    }

    @Override
    public void updateTodoStatus(Long todoId) {
        Todo todoForUpdate = todoRepository.findById(todoId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TODO_NOT_FOUND));

        todoForUpdate.switchStatus();
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoRepository.delete(todoRepository.findById(todoId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.TODO_NOT_FOUND)));
    }


}
