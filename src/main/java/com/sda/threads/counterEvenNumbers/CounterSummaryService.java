package com.sda.threads.counterEvenNumbers;

/**
 * Created by RENT on 2017-06-23.
 *
 * klasa - singleton do przechowywania licznika liczb parzystych
 * wiele watkow na raz bedzie zwiekszalo tutejszy licznik
 * wiec jego zwiekasznie musi byc synchronized
 */
public class CounterSummaryService {

    private int counter;

    private static CounterSummaryService ourInstance = new CounterSummaryService();

    public synchronized static CounterSummaryService getInstance() {
        return ourInstance;
    }

    private CounterSummaryService() {
        this.counter = 0;
    }

    //metoda ktora zwiekszy wartosc countera
    public synchronized void incementCounter() {
        counter++;
    }

    //getter
    public int getCounter() {
        return counter;
    }
}
