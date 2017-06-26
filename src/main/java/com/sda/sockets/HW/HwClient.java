package com.sda.sockets.HW;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 *
 * laczy sie do serwera
 * popodlaczeniu wysylaliczbe n
 * czeka na odpowiedz
 * po otrzymaniu wysyla ja w odwrtonej kolejnosci StringUtils.reverse
 */
public class HwClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner incomingScanner = new Scanner(socket.getInputStream());
        Scanner outgoingScanner = new Scanner(System.in);

        Random random = new Random();
        int i = random.nextInt(100);

        System.out.println("My random number: " + i);

        writer.write(i + "\n");
        writer.flush();

        System.out.println("Received result: ");
        String message = incomingScanner.nextLine();
        System.out.println(message);


        String reverse = StringUtils.reverse(message);
        System.out.println("Reversed result: " + reverse);

        writer.write(reverse + "\n");
        writer.flush();

        socket.close();



    }
}
