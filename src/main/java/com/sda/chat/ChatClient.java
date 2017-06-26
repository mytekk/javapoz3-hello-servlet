package com.sda.chat;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-26.
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {

        //Socket socket = new Socket("192.168.100.185", 1234);
        Socket socket = new Socket("localhost", 1234);

        Scanner socketIn = new Scanner(socket.getInputStream());
        BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner scanner = new Scanner(System.in);

        String input;

        loginToServer(socketOut, scanner);

        //towrzymy watek, ktory nonstop bedzie czytal to co przychodzi i bedzie to wyswietlal
        ChatClientReadTask chatClientReadTask = new ChatClientReadTask(socketIn);
        Thread thread = new Thread(chatClientReadTask);
        thread.start(); //watek wyswietla to co ktos wyslal

        //w petli tylko nadajemy
        do {
            input = scanner.nextLine();
            socketOut.write(input + "\n");
            socketOut.flush();


        } while (!"exit".equals(input));
        socket.close();
    }

    private static void loginToServer(BufferedWriter socketOut, Scanner scanner) throws IOException {
        System.out.println("Insert your nickName: ");
        String nickName = scanner.nextLine();
        socketOut.write(nickName + "\n");
        socketOut.flush();
    }
}
