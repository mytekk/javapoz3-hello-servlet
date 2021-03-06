package com.sda.servlets.todo;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-19.
 *
 * wlasciwa klasa implementujaca interfejs TodoDao
 * ta klasa właściwie implementuje sposób dostępu do danych zawartych w pliku
 */
public class TodoDaoFile implements TodoDao {

    private ServletContext servletContext; //stream do pliku z danymi
    private String path;

    //konstruktor
    public TodoDaoFile(ServletContext servletContext, String path) {
        this.servletContext = servletContext;
        this.path = path;
    }

    @Override
    public List<TodoModel> getAllTodos() {
        List<TodoModel> models = new ArrayList<>();

        try (
             //z kontekstu mojego servletu robie stream do pliku z danymi
             InputStream inputStream = servletContext.getResourceAsStream(path);
        ) {
            Scanner scanner = new Scanner(inputStream); //stworzony stream przekazuje do skanera
            while (scanner.hasNext()) {
                models.add(TodoMapper.map(scanner));
                //poniewaz wewnatrz powyzejmetody map czytam skanerem
                //5 linii, to tutaj w while nie musze juz
                //zajmowac sie przeskakiwaniem po 5 liniach
                //po pierwszym wyjsciu z add.map juz bede mial pierwszy todos
            }
        } catch (IOException e) {
            System.out.println("Couldn't close input stream.");
        }

        return models;
    }

    @Override
    public void addTodo(TodoModel todoModel) {
        try (
                OutputStream outputStream = new FileOutputStream(servletContext.getResource(path).getFile(), true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                ) {
            String mappedTodo = TodoMapper.map(todoModel);
            writer.write(mappedTodo);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
    }
}
