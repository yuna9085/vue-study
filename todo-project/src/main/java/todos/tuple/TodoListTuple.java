package todos.tuple;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import todos.Todo;

@Data
@JsonIgnoreProperties
public class TodoListTuple {

	private List<Todo> todoList;
	private Integer count;
	private Boolean result;

}