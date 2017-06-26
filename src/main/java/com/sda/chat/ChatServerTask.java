package com.sda.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 */
public class ChatServerTask implements Runnable {

    private ChatConnectionFacade yourConnection;
    private ChatUsersStorage storage;  //baza danych wszystkich userow

    //konstruktor
    public ChatServerTask(Socket socket, ChatUsersStorage storage) throws IOException {
        //tworzymy polaczenie
        Scanner in = new Scanner(socket.getInputStream());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        this.yourConnection = new ChatConnectionFacade(in, out);

        this.storage = storage;
    }

    @Override
    public void run() {

        //pobieramy nick clienta
        String nickName = yourConnection.read();
        yourConnection.setNickName(nickName);

        boolean add = storage.add(nickName, yourConnection);//dodajemy nową osobe do storyga

        if (add) {
            boolean flag = true;
            while (flag) {
                //czytmay to co przychodzi
                String input = yourConnection.read();
                String nickToSend = input.substring(0, input.indexOf(" ")); //z inputu wyciagam nick adresata
                //pobieramy polaczenie do wyszukanego usera
                ChatConnectionFacade destinationConnection = storage.get(nickToSend);
                //wysylamy to co bylo po nicku, ale nie do serwera tylko do tego drugiego usera
                try {
                    //this.yourConnection.write(input + " pong\n");
                    destinationConnection.write(input.substring(input.indexOf(" ")));
                } catch (NullPointerException e) {
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                yourConnection.write("Nickname already in use");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
