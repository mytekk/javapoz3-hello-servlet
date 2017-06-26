package com.sda.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 *
 * ta klasa symbolizuje polaczenie do uzytkownika
 * dostarcza mozliwosc pisania i czytania
 *
 * potrafi wyslac message do danego usera, a konkretniej do danego
 * BufferedWritera, ktorego otrzyma we wlasnym konstruktrze
 *
 * nasza fasada przedstawia proste metody, obudowuje skomplikowane rzeczy w metody read i write
 *
 */
public class ChatConnectionFacade {

    private Scanner in;
    private BufferedWriter out;
    private String nickName;

    //konstruktor
    public ChatConnectionFacade(Scanner in, BufferedWriter out, String nickName) {
        this.in = in;
        this.out = out;
        this.nickName = nickName;
    }

    //konstruktor 2
    public ChatConnectionFacade(Scanner in, BufferedWriter out) {
        this.in = in;
        this.out = out;
    }

    public void write(String message) throws IOException {
        System.out.println(nickName + ": " + message);
        out.write(message + "\n");
        out.flush();
    }

    public String read() {
        return in.nextLine();
    }

    //setter
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    //getter
    public String getNickName() {
        return nickName;
    }
}
