package com.sda.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by RENT on 2017-06-26.
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);

        boolean flag = true;

        ChatUsersStorage chatUsersStorage = new ChatUsersStorage(); //globalny storage userow

        while (flag) {

            Socket socket = serverSocket.accept(); //czekamy na nowe, kolejne polaczenie
            System.out.println("Someone connected");

            ChatServerTask chatServerTask = new ChatServerTask(socket, chatUsersStorage);
            //nowy obiekt, ktory bedzie przyjmowal to co przychodzi i przekierowywal dalej
            Thread thread = new Thread(chatServerTask); //nowy watek dla nowego usera
            thread.start();


        }
        serverSocket.close();
    }
}
