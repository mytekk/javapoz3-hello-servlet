package com.sda.warmup.moreless;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-22.
 */
public class GameServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1234);

        boolean tmp = true;
        while (tmp) {
            System.out.println("Waiting for connection from client");
            Socket socket = serverSocket.accept();
            System.out.println("Connection established");
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner socketIn = new Scanner(socket.getInputStream());

            //losowanie liczby
            Random random = new Random();
            int value = random.nextInt(100);
            System.out.println("Generated number: " + value);

            int compareResult;
            int steps = 0;

            do {
                //pobieram i wyswitlam wartosc od klienta
                String valueFromClient = socketIn.nextLine();
                System.out.println(valueFromClient);
                Integer incomeValue = Integer.valueOf(valueFromClient);

                //porownanie dwoch liczb jesli incomeValue > value to zwroci wartosc wieksza od zera itd...
                compareResult = Integer.compare(incomeValue, value);
                compareResult = Integer.signum(compareResult);

                //wyslanie wyniku do clienta
                socketOut.write(compareResult + "\n");
                socketOut.flush();

                socketOut.flush();

                steps++;
            } while (compareResult != 0);

            socketOut.write("Number of steps: " + steps + "\n");
            socketOut.flush();
            socket.close(); //zamykam obecne polaczenie
        }
        serverSocket.close();//zamkniecie calego socketu


    }

}
