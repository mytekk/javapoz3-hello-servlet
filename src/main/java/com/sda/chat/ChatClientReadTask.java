package com.sda.chat;

import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 *
 * klasa w petli bedzie robi in.readLine
 * czyta cos z socketu
 */
public class ChatClientReadTask implements Runnable {
    private Scanner in;

    public ChatClientReadTask(Scanner in) {
        this.in = in;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            System.out.println(in.nextLine());
        }
    }
}
