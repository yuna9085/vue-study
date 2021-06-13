package todos.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import todos.Todo;
import todos.data.TodoRepository;
import todos.tuple.TodoListTuple;

@Slf4j
@RestController
@RequestMapping("/api/todo")
public class TodoRestController {

	@Autowired TodoRepository todoRepository;
	
	@GetMapping("/list")
	public TodoListTuple listTodo(Model model) {
		TodoListTuple todoListTuple = new TodoListTuple();
		Boolean result = Boolean.FALSE;
	    try {
	    	List<Todo> todoList = null;
	    	todoList = todoRepository.findAll();
	    	if(todoList != null) {
	    		todoListTuple.setCount(todoList.size());
	    		todoListTuple.setTodoList(todoList);
	    	}
	    	result = Boolean.TRUE;
	    } catch(Exception e) {
	    	log.error(e.getMessage());
	    }
	    todoListTuple.setResult(result);
	    return todoListTuple;
	}
	
	@GetMapping("/save")
	public Boolean saveTodo(Model model, Todo todoSaveForm) {
	    Boolean result = Boolean.FALSE;
	    try {
	    	todoSaveForm.setInsertDtm(new Date());
	    	todoSaveForm.setModifyDtm(new Date());
	    	todoRepository.save(todoSaveForm);
	    	result = Boolean.TRUE;
	    	return result;
	    } catch(Exception e) {
	    	return result;
	    }
	}
	
	@GetMapping("/update")
	public Boolean updateTodo(Model model, Todo todoUpdateForm) {
	    Boolean result = Boolean.FALSE;
	    try {
	    	if(todoUpdateForm.getSeq() != null) {
	    		Todo todo = todoRepository.getOne(todoUpdateForm.getSeq());
	    		if(todo != null) {
	    			todo.setContents(todoUpdateForm.getContents());
	    			todo.setDoneYn(todoUpdateForm.getDoneYn());
	    			todo.setModifyDtm(new Date());
			    	todoRepository.save(todo);
			    	result = Boolean.TRUE;
	    		}
		    	return result;
	    	} else {
	    		return result;
	    	}
	    } catch(Exception e) {
	    	return result;
	    }
	}
	
	@GetMapping("/delete")
	public Boolean deleteTodo(Model model, Integer seq) {
	    Boolean result = Boolean.FALSE;
	    try {
	    	Todo todo = todoRepository.getOne(seq);
	    	if(todo != null) {
	    		todoRepository.deleteById(seq);
	    	}
	    	result = Boolean.TRUE;
	    	return result;
	    } catch(Exception e) {
	    	return result;
	    }
	}

}