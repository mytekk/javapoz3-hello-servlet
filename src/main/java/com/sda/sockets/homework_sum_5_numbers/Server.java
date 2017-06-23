package com.sda.sockets.homework_sum_5_numbers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-21.
 */
public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234); //odpowiada za nasluchiwanie
        //na nim moze byc wiele polaczen

        boolean flag = true;
        while (flag) {

            System.out.println("Waiting for connection");

            Socket socket = serverSocket.accept();//odpowiada za pojedyncze polaczenie i czeka na polaczenie

            Scanner incomingScanner = new Scanner(socket.getInputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Scanner outgoingScanner = new Scanner(System.in);

            int sum = 0;
            for (int i = 0; i < 5; i++) {
                int input = incomingScanner.nextInt();
                System.out.println("Received integer: " + input + "\n");
                sum += input;
            }

            writer.write("Sum: " + sum + "\n");
            writer.flush();

            System.out.println("Finisking connection");
            socket.close();
        }
        serverSocket.close();
    }

}
