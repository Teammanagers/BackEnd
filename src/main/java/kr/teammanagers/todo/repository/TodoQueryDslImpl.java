package kr.teammanagers.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.teammanagers.todo.domain.Todo;
import kr.teammanagers.todo.dto.request.UpdateTodo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TodoQueryDslImpl implements TodoQueryDsl{

    private final JPAQueryFactory queryFactory;

}
