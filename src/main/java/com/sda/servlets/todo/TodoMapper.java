package com.sda.servlets.todo;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-12.
 * klasa do mapowania zawartosci requesta na obiekt typu TodoModel
 * czyli na nowy obiekt to do
 */
public class TodoMapper {
    public static TodoModel map(HttpServletRequest req) {
        //robie mape parametrow z requesta
        Map<String, String[]> parameterMap = req.getParameterMap();

        //obiekt do zwrocenia
        TodoModel todoModelToReturn = new TodoModel();

        //teraz wyciagam poszczegolne parametry i mapuję na poszczególne pola
        //nowego obiektu klasy todoModel
        String name = parameterMap.get("name")[0]; //szukana wartosc znajduje sie w mapie pod kluczem "name", ktory jest tablica - faltyczna wartosc jest na pierwszym miejscu w tej tablicy
        todoModelToReturn.setName(name);

        String description = parameterMap.get("description")[0];
        todoModelToReturn.setDescription(description);

        todoModelToReturn.setChecked(getChecked(parameterMap));
        todoModelToReturn.setPriority(getPriority(parameterMap));
        todoModelToReturn.setDate(getDate(parameterMap));

        return todoModelToReturn;
    }

    private static boolean getChecked(Map<String, String[]> parameterMap) {
        return parameterMap.containsKey("checked"); //jesli przyszlo, to znaczy, ze jest "on"
        //jesli user nie zaznaczy checkboxa, to w mapie parametrow to w ogole nie przyjdzie
    }

    private static LocalDate getDate(Map<String, String[]> parameterMap) {
        String dateAsString = parameterMap.get("date")[0];
        String[] split = dateAsString.split("-");
        return LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
    }

    private static int getPriority(Map<String, String[]> parameterMap) {
        return Integer.parseInt(parameterMap.get("priority")[0]);
    }

    //przekazujemy 5 stringow
    //funkcja robi z nich nowy TodoModel
    public static TodoModel map(String title, String description, String status, String priority, String date) {
        TodoModel model = new TodoModel();

        model.setName(title);
        model.setDescription(description);
        //model.setChecked("true".equals(status));
        //model.setChecked(new Boolean(status));
        model.setChecked(Boolean.valueOf(status));
        model.setPriority(Integer.parseInt(priority));
        model.setDate(LocalDate.parse(date));

        return model;
    }

    //przekazujemy skaner do pliku
    //funkcja wezmie 5 linii ze skanera, przeczyta je
    //i zrobi z nich nowy TodoModel
    //wywoluje funkcje stworzona wyzej :)
    public static TodoModel map(Scanner scanner) {
        return map(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine());
    }

    //zamienia todosa na 5-linijkowy string gotowy do wrzucenia pozniej do pliku
    public static String map(TodoModel model) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(model.getName())
                .append("\r\n")
                .append(model.getDescription())
                .append("\r\n")
                .append(String.valueOf(model.isChecked()))
                .append("\r\n")
                .append(model.getPriority())
                .append("\r\n")
                .append(model.getDate().toString())
                .append("\r\n");

        return stringBuilder.toString();
    }

}
