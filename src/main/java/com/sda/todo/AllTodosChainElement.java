package com.sda.todo;

import java.util.List;

/**
 * Created by RENT on 2017-06-09.
 *
 * klasa odpowiedzialna za wyswietlenie wszystkich todosow, niezaleznie do tego
 * jaka scieżką (jakim url-em) wywołamy to, ze chcemy wyswietlic wszystkie todosy
 */
public class AllTodosChainElement extends AbstractTodoChainElementModel {

    public AllTodosChainElement(String path, TodoDao todoDao, TodoView todoView) {
        super(path, todoDao, todoView);
    }

    public String action() {
        //pobieram wszystkie todosy i zwracam w odpowiedniej formie (np. duży string )
        List<TodoModel> allTodos = todoDao.getAllTodos();
        return todoView.show(allTodos);
    }
}
