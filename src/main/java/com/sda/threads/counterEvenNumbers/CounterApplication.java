package com.sda.threads.counterEvenNumbers;

/**
 * Created by RENT on 2017-06-23.
 *
 * singleton dostarcza liste wartosci
 * inny singleton dostarcza licznik do przechowywania ilosci liczb parzystych w liscie wartosci
 *
 * aplikacja tworzy watki i liczy ile jest liczb parzystych w liście elementow
 */
public class CounterApplication {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Oto lista dosprawdzenia: " + CounterSummaryProvider.getInstance().getList().toString());

        CounterTask counterTask1 = new CounterTask();
        CounterTask counterTask2 = new CounterTask();
        CounterTask counterTask3 = new CounterTask();

        Thread thread1 = new Thread(counterTask1);
        Thread thread2 = new Thread(counterTask2);
        Thread thread3 = new Thread(counterTask3);

        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(500);
        System.out.println("Liczba elementow parzystych: " + CounterSummaryService.getInstance().getCounter());


    }
}
