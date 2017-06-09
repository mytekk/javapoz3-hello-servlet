package com.sda.todo;

import java.util.List;

/**
 * Created by RENT on 2017-06-08.
 *
 * imterfejs określający to jak będziemy mieli dostęp do danych
 */
public interface TodoDao {

    List<TodoModel> getAllTodos();

    void addTodo(TodoModel todoModel);

}
