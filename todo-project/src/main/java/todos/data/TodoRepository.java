package todos.data;

import org.springframework.data.jpa.repository.JpaRepository;

import todos.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
}