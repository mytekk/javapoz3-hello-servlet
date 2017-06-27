package com.sda.servlets.todo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        //todoDao = new TodoDaoMock();
        todoDao = new TodoDaoFile(getServletContext(), "/todo/data"); //przekazuje kontekst servletu i sciezke do pliku
        todoView = new TodoViewHtml();
        todoChain = new TodoChain(todoView, todoDao); //obiekt łańcucha!
    }

    //ta metoda obsluguje zapytanie GET tego servletu
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html; ISO-8859-1");
        writer.println(todoChain.invoke(req, resp)); //to co w nawiasie to serce logiki
    }
}
