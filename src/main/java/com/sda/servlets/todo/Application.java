package com.sda.servlets.todo;

import java.util.Scanner;

/**
 * Created by RENT on 2017-06-19.
 */
public class Application {

    public static void main(String[] args) {
        TodoDao todoDao = new TodoDaoMock();
        TodoView todoView = new TodoViewHtml();
        TodoChain todoChain = new TodoChain(todoView, todoDao);

        Scanner scanner = new Scanner(System.in);
        System.out.println("/all Wyswietl wszystkie");
        System.out.println("/add Dodaj");

        String answer = scanner.nextLine();

        /*todoChain.invoke(answer);*/
        //to nie zadziala, bo invoke potrzebuje dwoch
        //argumentow: request i response
        //tutaj ich nie mam, ale powyzszalinia jest symboliczna
        //gdyby umiec przerobic answer na request to to by poszło
        //i to byloby koniec przepisywania calego naszego programu na aplikacje konsolową

    }

}
