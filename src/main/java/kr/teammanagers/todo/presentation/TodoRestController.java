package kr.teammanagers.todo.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.todo.application.TodoCommandService;
import kr.teammanagers.todo.application.TodoQueryService;
import kr.teammanagers.todo.dto.request.CreateTodo;
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
}
