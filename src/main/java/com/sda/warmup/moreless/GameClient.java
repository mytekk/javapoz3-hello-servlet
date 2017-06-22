package com.sda.warmup.moreless;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-22.
 */
public class GameClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);
        BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner socketIn = new Scanner(socket.getInputStream());
        Scanner scannerFromUser = new Scanner(System.in); //skaner do czytanie od usera-klienta

        int result;

        do {

            //pobieram i wysylam liczbe do serwera
            System.out.print("Insert number: ");
            int myValue = scannerFromUser.nextInt();
            socketOut.write(myValue + "\n");
            socketOut.flush();

            //odbieram odp od serwera i wyswietlam
            String serwerResponse = socketIn.nextLine();
            System.out.println(serwerResponse);
            result = Integer.valueOf(serwerResponse);

            if (1 == result) {
                System.out.println("Value too high.");
            } else if (-1 == result) {
                System.out.println("Value too low");
            }

        } while (result != 0);

        System.out.println("Success!");
        String serwerResponse = socketIn.nextLine();
        System.out.println(serwerResponse);

        socket.close();
    }
}
