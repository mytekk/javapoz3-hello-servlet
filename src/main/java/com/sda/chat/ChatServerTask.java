package com.sda.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 *
 * klasa, ktora bedzie dzialac na watkach, uzywana w pliku ChatServer
 * na podstawie zadanego socketu tworzy scanner i writer, oraz obiekt klasy, ktory bedzie czytal od usera i pisal do usera
 * watek bedzie pobieral to co przychodzi od usera, ustalal adresata i wysylal mu wiadomosc
 */
public class ChatServerTask implements Runnable {

    private ChatConnectionFacade yourConnection; //obiekt klasy obslugujacej polaczenie z danym userem
    private ChatUsersStorage storage;  //baza danych wszystkich userow

    //konstruktor
    public ChatServerTask(Socket socket, ChatUsersStorage storage) throws IOException {
        //dostalismy socket, na jego podstawie tworzymy polaczenie
        Scanner in = new Scanner(socket.getInputStream());
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //inicjalizacja obiektu klasy obslugujacej polaczenie z danym userem
        this.yourConnection = new ChatConnectionFacade(in, out);

        //inicjalizacja bazy danych wszystkich userow
        this.storage = storage;
    }

    @Override
    public void run() {

        //pobieramy nick clienta (pierwsze co klient robi po nawiazaniu polaczenia to wlasnie wysłanie nam swojego nicku)
        String nickName = yourConnection.read();
        yourConnection.setNickName(nickName); //aktualizujemy pole nickName w obiekcie, ktory bedzie czytal od naszego usera i wysylal do naszego usera

        boolean add = storage.add(nickName, yourConnection);//dodajemy nową osobe do storyga

        if (add) {
            boolean flag = true;
            while (flag) {
                //czytamy to co przychodzi od usera (od usera przychodzi wiadomosc do innego usera)
                String input = yourConnection.read();

                //z inputu wyciagam nick adresata, czyli do pierwszej spacji
                String nickToSend = input.substring(0, input.indexOf(" "));

                //pobieramy polaczenie do wyszukanego usera z bazy wszystkich połączeń
                ChatConnectionFacade destinationConnection = storage.get(nickToSend);

                //wysylamy to co bylo po nicku, ale nie do serwera tylko do tego drugiego usera
                try {
                    destinationConnection.write(input.substring(input.indexOf(" ")));
                } catch (NullPointerException e) {
                    //na wszelki wypadek
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
