package com.sda.todo;

import java.util.List;

/**
 * Created by RENT on 2017-06-08.
 */
public interface TodoView {
    String show(List<TodoModel> todos);
    String show(TodoModel model);
}
