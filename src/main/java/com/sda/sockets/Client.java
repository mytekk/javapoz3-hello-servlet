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

        Socket socket = new Socket("localhost", 1234); //tworzymy socket, klassa sluzaca do podłączania sie do serwera

        //chcemy cos nadać na zewnatrz
        //writer bedzie wypisywal od nas na zewnatrz
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //skaner do tego co do nas przychodzi
        Scanner scanner = new Scanner(socket.getInputStream());

        //skaner dla usera
        Scanner scannerToUser = new Scanner(System.in);

        boolean flag = true;
        while (flag) {
            //pobieramy tekst od usera i wysylamy na socket
            System.out.print("Napisz wiadomosc: ");
            writer.write(scannerToUser.nextLine() + "\n");
            writer.flush();

            System.out.println("Received message: ");
            System.out.println(scanner.nextLine()); //czeka dopoki nie pojawi sie nowa linia z Serwera
        }

        socket.close();

    }
}
