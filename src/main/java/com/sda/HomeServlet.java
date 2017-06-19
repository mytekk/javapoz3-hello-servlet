package com.sda;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-08.
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("********** Mój hello world!!! *************"); //to idzie do logu!
        PrintWriter writer = resp.getWriter();  //odpowiednik system.out
        resp.setContentType("text/html");
        writer.println("<h1>Hello world!</h1>");

        //teraz dobieram sie do pliku test.txt w folderze webapp
        //i w petli wyswietlam wiersze z tego pliku

        InputStream stream = getServletContext().getResourceAsStream("/test.txt");
        Scanner scanner = new Scanner(stream);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ol>\n");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            stringBuilder.append("<li>")
                .append(line)
                .append("</li>\n");
        }

        stringBuilder.append("</ol>\n");
        String output = stringBuilder.toString();
        writer.println(output);

    }

}
