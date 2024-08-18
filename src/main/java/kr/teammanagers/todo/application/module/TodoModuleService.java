package kr.teammanagers.todo.application.module;

import kr.teammanagers.todo.domain.Todo;

import java.util.List;

public interface TodoModuleService {
    List<Todo> getTodoListByTeamManageId(Long teamManageId);
}
