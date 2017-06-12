package com.sda.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    public String action(HttpServletRequest req, HttpServletResponse resp) {
        String valueToReturn = "<h1>OK</h1>";

        //czy przyszedl pusty request, czy pelny?
        if (TodoUtil.isWriteRequest(req)) {
            //tworze nowego todosa
            TodoModel newTodoModel = TodoMapper.map(req);
            //zapisuje go
            todoDao.addTodo(newTodoModel);
        } else {
            //przyszedl pusty request, wiec tutaj zwracam
            //stringa z htmlmowym formularzem dodawania nowego todosa
            valueToReturn = todoView.showAddForm();
        }

        return valueToReturn;
    }

}
