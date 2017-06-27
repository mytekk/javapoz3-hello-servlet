package com.sda.chat;

import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 *
 * klasa w petli bedzie robi socketIn.readLine
 * czyta cos z socketu i wypisuje to na konsoli
 */
public class ChatClientReadTask implements Runnable {

    private Scanner in;

    //konstruktor
    //w konstruktorze przychodzi skaner do strumienia wejsciowego (socketIn)
    public ChatClientReadTask(Scanner in) {
        this.in = in;
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            //jedyne co robi ten watek to czyta ze skanera i wypisuje to na konsoli
            System.out.println(in.nextLine());
        }
    }
}
