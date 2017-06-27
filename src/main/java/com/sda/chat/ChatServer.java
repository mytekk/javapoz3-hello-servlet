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

        ChatUsersStorage chatUsersStorage = new ChatUsersStorage(); //obiekt globalny storage userow

        while (flag) {

            //czekamy na nowe, kolejne polaczenie
            Socket socket = serverSocket.accept();
            System.out.println("Someone connected");

            //nowy obiekt, ktory bedzie obslugiwal polaczenie z nowym userem: bedzie przyjmowal to co od niego przychodzi
            // i przekierowywal dalej, do adresata, czyli innego usera
            //w tej klasie zawarta jest logika dotyczaca przesylania wiaodmosci do odpowiednich nadawcow
            ChatServerTask chatServerTask = new ChatServerTask(socket, chatUsersStorage);

            //nowy watek dla nowego polaczenia z userem
            //nowych watkow bedzie tyle uli userow podlaczy sie do serwera!
            Thread thread = new Thread(chatServerTask);
            thread.start();
        }
        serverSocket.close();
    }
}
