package com.sda.threads.DemoAndSumListOfIntegers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-06-23.
 *
 * klasa - singleton
 * sluzy do przechowywania sum, które obliczane beda przez rozne wątki
 * te sumy przechowywane sa w liscie
 * wątek dziala w ten sposob, ze liczy swoja sume i do Summera dorzuca swoj wynik
 */
public class Summer {

    private List<Integer> list;

    private static Summer instance = null;

    public synchronized static Summer getInstance() {
        if (instance == null) {
            instance = new Summer();
        }
        return instance;
    }

    private Summer() {
        this.list = new ArrayList<>();
    }

    //dodaje sume to listy zebranych sum
    public synchronized void add(int sum) {
        list.add(sum);
    }

    //zwraca sume wszystkich zebranych sum
    public int sumElements() {
        int sum = list.stream()
                .mapToInt(e -> e) //zwroci intStream, a strumienintow posiada metode sum
                .sum();
        return sum;
    }

    //wypisuje zebrane wartosci
    public String getNumbers() {
        return list.toString();
    }

    //metoda psujaca
    public synchronized void test() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Hello, I am summing!");
    }
}
