package kr.teammanagers.todo.application;

import kr.teammanagers.common.payload.code.GeneralException;
import kr.teammanagers.common.payload.code.status.ErrorStatus;
import kr.teammanagers.team.domain.Team;
import kr.teammanagers.team.domain.TeamManage;
import kr.teammanagers.team.repository.TeamManageRepository;
import kr.teammanagers.team.repository.TeamRepository;
import kr.teammanagers.todo.domain.Todo;
import kr.teammanagers.todo.dto.TodoDto;
import kr.teammanagers.todo.dto.TodoListDto;
import kr.teammanagers.todo.dto.response.GetTodoList;
import kr.teammanagers.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoQueryServiceImpl implements TodoQueryService {

    private final TodoRepository todoRepository;
    private final TeamManageRepository teamManageRepository;

    @Override
    public GetTodoList getTodoList(Long teamId) {

        List<TeamManage> teamManageList = teamManageRepository.findAllByTeamId(teamId);

        List<Long> teamManageIdList = teamManageList.stream()
                .map(teamManage -> { return teamManage.getId(); })
                .collect(Collectors.toList());

        List<String> nameList = teamManageList.stream()
                .map(teamManage -> { return teamManage.getMember().getName(); })
                .collect(Collectors.toList());

        List<List<Todo>> teamTodoList = teamManageList.stream()
                .map(teamManage -> { return todoRepository.findAllByTeamManage(teamManage); })
                .collect(Collectors.toList());

        List<List<TodoDto>> teamTodoDtoList = teamTodoList.stream()
                .map(todoList -> { return todoList.stream()
                        .map(todo -> { return TodoConverter.toTodoDto(todo); }).collect(Collectors.toList()); })
                .collect(Collectors.toList());

        List<TodoListDto> teamTodoListDtoList = new ArrayList<>();
        for (int i = 0; i < teamManageIdList.size(); i++) {
            TodoListDto newTodoListDto = TodoListDto.builder()
                    .teamManageId(teamManageIdList.get(i))
                    .name(nameList.get(i))
                    .todoList(teamTodoDtoList.get(i))
                    .build();
            teamTodoListDtoList.add(newTodoListDto);
        }   //todo: tagDtoList 추가

        Integer numOfTodo = 0;
        Integer numOfTodoCompleted = 0;

        for (List<Todo> list : teamTodoList) {
            numOfTodo += list.size();
        }

        List<List<Todo>> completedTeamTodoList = teamTodoList.stream()
                .map(todoList -> { return todoList.stream()
                        .filter(todo -> { return todo.checkCompleted(todo); }).collect(Collectors.toList()); })
                .collect(Collectors.toList());

        for (List<Todo> list : completedTeamTodoList) {
            numOfTodoCompleted += list.size();
        }


        Integer progress = numOfTodoCompleted / numOfTodo;   //todo: progress 계산 추가

        return GetTodoList.builder()
                .teamTodoList(teamTodoListDtoList)
                .progress(progress)
                .build();
    }
}
