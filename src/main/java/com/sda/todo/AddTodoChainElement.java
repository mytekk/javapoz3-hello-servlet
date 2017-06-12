package com.sda.todo;

/**
 * Created by RENT on 2017-06-09.
 *
 * klasa odpowiedzialna za dodawanie todosow, niezaleznie do tego
 * jaka scieżką (jakim url-em) wywołamy to, ze chcemy dodac nowe todo
 */
public class AddTodoChainElement extends AbstractTodoChainElementModel {

    public AddTodoChainElement(String path, TodoDao todoDao, TodoView todoView) {
        super(path, todoDao, todoView);
    }

    public String action() {
        //tutaj stringa z htmlmowym formularzem dodawania nowego todosa
        return todoView.showAddForm();
    }

}
