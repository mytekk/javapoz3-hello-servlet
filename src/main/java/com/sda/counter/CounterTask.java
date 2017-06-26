package com.sda.counter;

/**
 * Created by RENT on 2017-06-23.
 *
 * klasa implementujaca interfejs  runable - moze byc wykonywana na watkach
 * bierze liczbe, sprawdza czy jest parzysta i jesli tak to zwieksza counter w klasie CounterSummaryService
 */
public class CounterTask implements Runnable {

    @Override
    public void run() {

        CounterSummaryProvider provider = CounterSummaryProvider.getInstance();
        CounterSummaryService service = CounterSummaryService.getInstance();
        int threadOperationsCounter = 0;

        while (provider.hasNext()) {
            //z listy biore kolejny element do sprawdzenia
            int valueToCheck = provider.getNextValue();
            threadOperationsCounter++;

            //System.out.print("\nSprawdzam wartosc: " + valueToCheck);

            //sprawdzam, czy pobrana wartosc jest parzysta
            //jesli jest, to podbijam counter
            if (valueToCheck % 2 == 0) {
                service.incementCounter();
                //System.out.println(" ta wartosc jest parzysta\n");
            }

        }
        System.out.println("Ten watek obsluzyl: " + threadOperationsCounter + " elementow.");
        //wyswietlam liczba parzystych liczb z listy
        //service.getCounter();
    }
}
