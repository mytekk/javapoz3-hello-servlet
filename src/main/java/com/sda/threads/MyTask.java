package com.sda.threads;

/**
 * Created by RENT on 2017-06-22.
 *
 * zwykla klasa implementujaca interfejs  runable - dzieki niemu moge stworzyc wątek
 */
public class MyTask implements Runnable {

    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        //try {
        //    Thread.sleep(1000);
            System.out.println("Hello world from " + name + " thread!");
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
    }
}
