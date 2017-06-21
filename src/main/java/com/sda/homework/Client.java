package com.sda.homework;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-21.
 */
public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Scanner incomingScanner = new Scanner(socket.getInputStream());

        Scanner outgoingScanner = new Scanner(System.in);

        for (int i = 0; i < 5; i++) {
            System.out.print("Podaj integer: ");
            writer.write(outgoingScanner.nextInt() + "\n");
            writer.flush();
        }

        System.out.println("Received result: ");
        System.out.println(incomingScanner.nextLine());

        socket.close();

        //dodac obsluge zajetego polaczenia
        //pierwsza liczba ozaczaliczbe libcz do wyslania

    }
}
