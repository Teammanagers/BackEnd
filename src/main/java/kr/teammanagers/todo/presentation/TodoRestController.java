package kr.teammanagers.todo.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.todo.application.TodoCommandService;
import kr.teammanagers.todo.application.TodoQueryService;
import kr.teammanagers.todo.dto.request.CreateTodo;
import kr.teammanagers.todo.dto.request.UpdateTodo;
import kr.teammanagers.todo.dto.response.GetTodoList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoRestController {

    private final TodoCommandService todoCommandService;
    private final TodoQueryService todoQueryService;

    @PostMapping("/team/{teamManageId}/todo")
    public ApiPayload<Void> create(@RequestBody CreateTodo request,
                                   @PathVariable Long teamManageId) {

        todoCommandService.createTodo(request, teamManageId);
        return ApiPayload.onSuccess(null);
    }

    @GetMapping("/team/{teamId}/todo")
    public ApiPayload<GetTodoList> get(@PathVariable Long teamId) {

        GetTodoList getTodoList = todoQueryService.getTodoList(teamId);

        return ApiPayload.onSuccess(getTodoList);
    }

    @PatchMapping("/todo/{todoId}")
    public ApiPayload<Void> updateTitle(@RequestBody UpdateTodo request,
                                        @PathVariable Long todoId) {
        //Todo: 수정된 Todo 리턴 안해도 되나?
        todoCommandService.updateTodoTitle(request, todoId);

        return ApiPayload.onSuccess(null);
    }

    @PatchMapping("/todo/{todoId}/state")
    public ApiPayload<Void> updateStatus(@PathVariable Long todoId) {

        todoCommandService.updateTodoStatus(todoId);

        return ApiPayload.onSuccess(null);
    }
}
