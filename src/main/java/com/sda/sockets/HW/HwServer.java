package com.sda.sockets.HW;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 *
 * ser czeka na poslaczanie
 * po podlaczneiu osbiera liczbe n
 * wysyla losowego stringa o rozmiarze n
 */
public class HwServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);

        boolean flag = true;
        while (flag) {

            System.out.println("Waiting for connection");

            Socket socket = serverSocket.accept();
            System.out.println("Connection established");
            Scanner incomingScanner = new Scanner(socket.getInputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String inputString = incomingScanner.nextLine();
            int input = Integer.parseInt(inputString);
            System.out.println("Received integer: " + input + "\n");

            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();

        /*
        for (int i = 0; i < input; i++) {
            int index = random.nextInt(255);
            stringBuilder.append((char)index);
        }
            //String outgoingString = stringBuilder.toString();
            */

            String outgoingString = RandomStringUtils.random(input, true, true);
            writer.write(outgoingString + "\n");
            writer.flush();

            String reversedString = incomingScanner.nextLine();
            System.out.println("Reversed string: " + reversedString);

            System.out.println("Finisking connection");
            socket.close();
        }
        serverSocket.close();
    }
}
