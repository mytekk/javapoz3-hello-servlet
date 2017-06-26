package com.sda.counter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by RENT on 2017-06-23.
 *
 * klasa - singleton ktora bedzie bedzie dostarczala liste elementow do sprawdzenia
 * wiele watkow ma pracowac na jednym obiekcie
 */
public class CounterSummaryProvider {

    private List<Integer> list;
    private Iterator<Integer> iterator;

    private static CounterSummaryProvider ourInstance = new CounterSummaryProvider();

    public synchronized static CounterSummaryProvider getInstance() {
        return ourInstance;
    }

    //konstruktor
    private CounterSummaryProvider() {
        initList();
        iterator = list.iterator();
    }

    //pomocnicza metoda inicjujaca
    private void initList() {
        //generujemy liste losowych liczb
        Random random = new Random();
        list = new ArrayList<Integer>();
        for (int i = 0; i < 300; i++) {
            list.add(random.nextInt(100));
        }
    }

    //getter
    public List<Integer> getList() {
        return list;
    }

    //metoda zwracajaca kolejną wartość do sprawdzenia
    //z tej metody bedzie korzystalo wiele watkow na raz
    //najpierw biore pierwszy element, drugi, trzeci, az do konca listy
    //to, ktory element zwrocic przechowuje w osobnym polu tej klasy
    //wersja lepsza: zaatwia to samo za pomoca iteratora!!!
    public synchronized int getNextValue() {
        //moja wersja
        /*
        int valueToReturn = -1;
        if (this.hasNext()) {
            valueToReturn = list.get(indexOfCheckedElement);
            indexOfCheckedElement++;
        }
        return valueToReturn;
        */
        //lepsza wersja
        if (hasNext()) {
            return iterator.next();
        }
        return 1; //jesli przyszlo do metody getNextValue, a jej nie ma (false w hasNext) to zwroc 1 dla bezpieczenstwa
    }

    //czy w liscie sa jeszcze wartosci do sprawdzenia?
    public synchronized boolean hasNext() {
        //moja wersja
        //return indexOfCheckedElement < list.size();
        //lepsza wersja
        return iterator.hasNext();
    }

}
