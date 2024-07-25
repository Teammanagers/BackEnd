package kr.teammanagers.todo.repository;

import kr.teammanagers.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoQueryDsl {
}
