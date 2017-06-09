package com.sda.todo;

import java.util.List;

/**
 * Created by RENT on 2017-06-09.
 *
 * klasa odpowiedzialna za wyswietlenie wszystkich todosow, niezaleznie do tego
 * jaka scieżką (jakim url-em) wywołamy to, ze chcemy wyswietlic wszystkie todosy
 */
public class AllTodosChainElement implements TodoChainElement {

    private String path;

    private TodoDao todoDao;
    private TodoView todoView;

    public AllTodosChainElement(String path, TodoDao todoDao, TodoView todoView) {
        this.path = path;
        this.todoDao = todoDao;
        this.todoView = todoView;
    }

    @Override
    public boolean isMyResponsibility(String path) {
        return this.path.equals(path);  //jesli sciezka (która wcześniej, w konstruktorze zdefiniowałem
        // jako właściwą dla obsługiwania wyświetlenia wszystkich todosów ) będzie taka sama jak ta,
        //która przyszła w requeście, to wtedy obsłużymy takie żądanie
    }

    @Override
    public String action() {
        //pobieram wszystkie todosy i zwracam w odpowiedniej formie (np. duży string )
        List<TodoModel> allTodos = todoDao.getAllTodos();
        return todoView.show(allTodos);
    }
}
