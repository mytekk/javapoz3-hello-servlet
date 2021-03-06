package com.sda.servlets.todo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by RENT on 2017-06-09.
 *
 * kregoslup - chain of responsibility
 * elementami kregoslupa będa klasy, które obsługuja poszczególne ściezki URLowe
 */
public class TodoChain {

    private List<TodoChainElement> chainElements;

    private TodoView todoView;
    private TodoDao todoDao;

    public TodoChain(TodoView todoView, TodoDao todoDao) {
        this.chainElements = new ArrayList<>();
        this.todoView = todoView;
        this.todoDao = todoDao;
        init();
    }

    //ta metoda wypełnia naszą listę dostępnych elementów łańcucha odpowiedzialności
    //tutaj ustawiamy elementy łańcicha
    //kolejnosc ma znaczenie!
    private void init() {
        chainElements.add(new AllTodosChainElement("/all", todoDao, todoView));
        chainElements.add(new AddTodoChainElement("/add", todoDao, todoView));
    }

    //metoda do pobudzania łańcucha
    public String invoke(HttpServletRequest req, HttpServletResponse resp) {
        //szukamy, ktora implementacja obsluzy zadana sciezke
        Iterator<TodoChainElement> iterator = chainElements.iterator();
        boolean flag = false;
        TodoChainElement finalElement = null; //szukany chainElement, ktory obsluzy nasze żądanie

        //iteratorem przechodze po wszystkich elementach "kregoslupa"
        //i szukam tego, ktory jest odpowiedzialny za obsluge mojej sciazki
        while (!flag && iterator.hasNext()) {
            TodoChainElement nextElement = iterator.next();//pobieram element
            if (nextElement.isMyResponsibility(req.getPathInfo())) { //czy obsluzy zapytanie?
                finalElement = nextElement;
                flag = true;
            }
        }
        //jak znajde to wykonuje metode invoke i przekazuję znaleziony odpowiedzialny element
        return invoke(finalElement, req, resp);
    }

    private String invoke(TodoChainElement chainElement, HttpServletRequest req, HttpServletResponse resp) {
        if (chainElement != null) {
            return chainElement.action(req, resp);
        } else {
            return "<h1>Cannot find page!</h1>";
        }

        //albo
        //return chainElement != null ? chainElement.action() : "<h1>Cannot find page!</h1>";
    }

}
