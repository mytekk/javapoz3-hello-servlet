package com.sda.threads;

import java.util.Arrays;

/**
 * Created by RENT on 2017-06-22.
 */
public class Application {
    public static void main(String[] args) {

        MyTask myTask = new MyTask("one");
        MyTask myTask2 = new MyTask("two");
        MyTask myTask3 = new MyTask("three");

        //wariant synchroniczny
        //myTask.run(); // uruchomią się po kolei
        //myTask2.run();
        //myTask3.run();

        System.out.println("======");

        //wariant asynchroniczny
        //uruchomia sie asynchronicznie, wszystkie 3 na raz
        //choc nadal bedzie jakas kolejnosc startu poszczegolnych watkow
        //to juz zalezy od procka i szybkocsi przydzialu pamieci na poszczegolne watki
        //a zatem wiadomosc z poszczegolnych watkow wyswietli sie w jakijs przypadkowej kolejnosci
        //w szczegolnosci jakis watek moze byc szybszy niz wypisanie tekstow "starting thread two"
        //lub "starting thread three"!
        //chociaz napis "Starting thread one" zawsze bedzie pierwszy
        /*
        Thread thread = new Thread(myTask);
        Thread thread2 = new Thread(myTask2);
        Thread thread3 = new Thread(myTask3);

        System.out.println("Starting thread one");
        thread.start();
        System.out.println("Starting thread two");
        thread2.start();
        System.out.println("Starting thread three");
        thread3.start();

        //watkow nie mozna uruchomiac ponownie ani restartowac
        //mozna je wystartowac tylko raz
        */

        //wariant asynchroniczny, poszczegolnewatki sumuja liste integerow ze swoich obiektow
        System.out.println("=================");

        SumTask sumTask1 = new SumTask(Arrays.asList(8, 4, 9, 9, 5, 8, 3));
        SumTask sumTask2 = new SumTask(Arrays.asList(1, 1, 1, 1, 1, 1, 1));
        SumTask sumTask3 = new SumTask(Arrays.asList(823, 4123, 9546, 9234, 5765, 82355 ,234233));

        Thread sumThread1 = new Thread(sumTask1);
        Thread sumThread2 = new Thread(sumTask2);
        Thread sumThread3 = new Thread(sumTask3);

        //startujemy watki
        sumThread1.start();
        sumThread2.start();
        sumThread3.start();

    }
}
