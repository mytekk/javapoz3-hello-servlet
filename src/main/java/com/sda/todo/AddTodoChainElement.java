package com.sda.todo;

/**
 * Created by RENT on 2017-06-09.
 *
 * klasa odpowiedzialna za dodawanie todosow, niezaleznie do tego
 * jaka scieżką (jakim url-em) wywołamy to, ze chcemy dodac nowe todo
 */
public class AddTodoChainElement implements TodoChainElement {

    private String path;

    private TodoDao todoDao;
    private TodoView todoView;

    public AddTodoChainElement(String path, TodoDao todoDao, TodoView todoView) {
        this.path = path;
        this.todoDao = todoDao;
        this.todoView = todoView;
    }

    @Override
    public boolean isMyResponsibility(String path) {
        return this.path.equals(path);
    }

    @Override
    public String action() {
        //tutaj stringa z htmlmowym formularzem dodawania nowego todosa
        return todoView.showAddForm();
    }

}
