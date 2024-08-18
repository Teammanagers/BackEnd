package kr.teammanagers.todo.application.module;

import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.global.exception.GeneralException;
import kr.teammanagers.todo.domain.Todo;
import kr.teammanagers.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoModuleServiceImpl implements TodoModuleService {

    private final TodoRepository todoRepository;

    @Override
    public List<Todo> getTodoListByTeamManageId(Long teamManageId) {
        return todoRepository.findAllByTeamManageId(teamManageId);
    }
}
