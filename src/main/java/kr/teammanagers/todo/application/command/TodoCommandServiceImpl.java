package kr.teammanagers.todo.application.command;

import kr.teammanagers.global.config.AmazonConfig;
import kr.teammanagers.global.provider.AmazonS3ProviderV3;
import kr.teammanagers.team.application.module.TeamModuleService;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.todo.application.module.TodoModuleService;
import kr.teammanagers.todo.domain.Todo;
import kr.teammanagers.todo.dto.request.CreateTodo;
import kr.teammanagers.todo.dto.request.UpdateTodo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class TodoCommandServiceImpl implements TodoCommandService {

    private final TodoModuleService todoModuleService;
    private final TeamModuleService teamModuleService;
    private final AmazonS3ProviderV3 amazonS3ProviderV3;
    private final AmazonConfig amazonConfig;

    @Override
    public void createTodo(CreateTodo request, Long teamManageId) {
        Todo newTodo = request.toTodo();
        newTodo.setTeamManage(teamModuleService.findById(teamManageId, TeamManage.class));

        todoModuleService.saveTodo(newTodo);
    }

    @Override
    public void updateTodoTitle(UpdateTodo request, Long todoId) {
        Todo todoForUpdate = todoModuleService.getTodoById(todoId);

        todoForUpdate.changeTitle(request.title());
    }

    @Override
    public void updateTodoStatus(Long todoId, Integer option) {
        Todo todoForUpdate = todoModuleService.getTodoById(todoId);

        todoForUpdate.changeStatus(option);
    }

    @Override
    public void deleteTodo(Long todoId) {
        todoModuleService.deleteTodoById(todoId);
    }

    @Override
    public void uploadTodoImage(Long teamId, Long todoId, MultipartFile image) {
        Todo todoForUpload = todoModuleService.getTodoById(todoId);

        todoForUpload.setImageUrl(amazonS3ProviderV3.uploadImage(amazonConfig.getTodoImagePath(), teamId, image));
    }


}
