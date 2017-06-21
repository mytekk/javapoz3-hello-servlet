package com.sda.sockets;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-20.
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234); //tworzymy socket; klasa Socket sluzy do podłączania sie do serwera

        //writer wypisuje na zewnatrz cos co bedziemy chcieli wyslac
        //socket.getOutputStream - strumien wyjsciowy od nas
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //skaner do czytania tego co do nas przychodzi, czyli skaner tego co przychodzi do nas od Servera
        Scanner scanner = new Scanner(socket.getInputStream());

        //skaner do usera "lokalnego", skaner do wpisywania tego co my bedziemy wysylac
        Scanner scannerToUser = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            //pobieramy tekst od usera i wysylamy na socket
            System.out.print("Napisz wiadomosc: ");
            writer.write(scannerToUser.nextLine() + "\n");  //czeka na podanie naszego tekstu
            writer.flush(); //faktyczne wysłanie naszego tekstu na strumien wyjsciowy

            System.out.println("Received message: ");
            System.out.println(scanner.nextLine()); //czeka dopoki nie pojawi sie nowa linia z Serwera
        }

        socket.close();

    }
}
