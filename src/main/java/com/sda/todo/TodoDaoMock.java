package com.sda.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-06-08.
 *
 * wlasciwa klasa implementujaca interfejs TodoDao
 * ta klasa właściwie implementuje sposób dostępu do mockowych danych
 */
public class TodoDaoMock implements TodoDao {

    private List<TodoModel> todos;

    public TodoDaoMock() {
        todos = new ArrayList<>();
        init();
    }

    @Override
    public List<TodoModel> getAllTodos() {
        return todos;
    }

    @Override
    public void addTodo(TodoModel todoModel) {
        todos.add(todoModel);
    }

    //dodaje dane do listy rzeczy do zrobienia
    private void init() {
        todos.add(new TodoModel("Zadanie domowe", "zadanie 3 ze strony 10", false,
                4, LocalDate.now()));
        todos.add(new TodoModel("Spotkanie z kimś", "wyjście na kręgle", false,
                3, LocalDate.now().minusDays(4)));
        todos.add(new TodoModel("Zakupy", "pojechać do biedry na zakupy", false,
                5, LocalDate.now().plusDays(2)));
    }
}
