package kr.teammanagers.todo.presentation;

import kr.teammanagers.common.payload.code.ApiPayload;
import kr.teammanagers.todo.application.TodoCommandService;
import kr.teammanagers.todo.dto.request.CreateTodo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoRestController {

    private final TodoCommandService todoCommandService;

    @PostMapping("/team/{teamManageId}/todo")
    public ApiPayload<Void> create(@RequestBody CreateTodo request,
                                   @PathVariable Long teamManageId) {

        todoCommandService.createTodo(request, teamManageId);
        return ApiPayload.onSuccess(null);
    }
}
