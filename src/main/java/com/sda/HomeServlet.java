package com.sda;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by RENT on 2017-06-08.
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("********** Mój hello world!!! *************");
        PrintWriter writer = resp.getWriter();  //odpowiednik system.out
        resp.setContentType("text/html");
        writer.println("<h1>Hello world!</h1>");
    }

}
