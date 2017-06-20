package com.sda.sockets;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-20.
 */
public class Server {


    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234); //otwieramy drzwi i czekamy na polaczenia (nasluchujemy)
        System.out.println("Waiting fo rconnection");

        Socket socket = serverSocket.accept(); //jak ktos sie podlaczy, to ustanawiana jest
        //dwukierunkowa komunikacja. Zwracana jest zmienna socket symbolizujaca ten kanał.
        //tutaj prpgram sie zawiesza i czeka az jakis Client sie podlaczy
        //jak ktos sie podlaczy, to blokada jest zwalniana
        System.out.println("Connection established");

        //skaner do czytania tego co nas przychodzi
        Scanner scanner = new Scanner(socket.getInputStream());

        //writer wypisuje na zewnatrz cos co bedziemy chcieli wyslac
        //socket.getOutputStream - strumien wyjsciowy od nas
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //skaner do usera
        Scanner scannerToUser = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            String input = scanner.nextLine(); //czeka dopoki nie pojawi sie nowa linia z Klienta
            System.out.println("Received message: " + input);

            writer.write(scannerToUser.nextLine() + "\n");
            //System.out.println("Flushing output");
            writer.flush(); //faktyczne wysłanie na strumien wyjsciowy
        }

        socket.close();
        serverSocket.close();
    }




}
