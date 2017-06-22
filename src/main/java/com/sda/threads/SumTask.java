package com.sda.threads;

import java.util.List;

/**
 * Created by RENT on 2017-06-22.
 */
public class SumTask implements Runnable {

    private List<Integer> list;

    public SumTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int element : list) {
            sum += element;
        }
        System.out.println("My sum is: " + sum);
    }
}
