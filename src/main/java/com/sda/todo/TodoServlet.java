package com.sda.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by RENT on 2017-06-08.
 *
 * główna klasa z servletem
 */
public class TodoServlet extends HttpServlet {

    private TodoDao todoDao;
    private TodoView todoView;

    //instancja łańcucha
    private TodoChain todoChain;

    //wywolywane po utworzeniu obiektu, odpowiednik before w testach
    //jest po to, zeby nie trzeba bylo za kazdym nowym requestem
    //budowac listy wszystkich todos
    //normalnie musielibysmy te liste tworzyc zakazdym razem w funkcji
    //doGet (za kazdym requestem) co byloby nieefektywne
    @Override
    public void init() throws ServletException {
        todoDao = new TodoDaoMock();
        todoView = new TodoViewHtml();
        todoChain = new TodoChain(todoView, todoDao);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html; ISO-8859-1");
        writer.println(todoChain.invoke(req.getPathInfo()));
    }
}
