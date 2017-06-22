package com.sda.kik;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-20.
 * <p>
 * serwer bedzie wpisywal krzyzyki na boarda "X"
 * czyli niedosc,ze jest serwerem, to jeszcze jest jednym z graczy
 */
public class KikServer {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1235);
        System.out.println("Waiting for connection from client");
        Socket socket = serverSocket.accept();
        BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner socketIn = new Scanner(socket.getInputStream());
        Scanner scanner = new Scanner(System.in); //skaner do czytanie od usera-serwera

        Board board = new Board();

        boolean flag = true;
        System.out.println("You are first");
        boolean status;

        while (flag) {
            //wyswietlenie tablicy
            System.out.println("Updated board from client:");
            System.out.println(board.toString());

            do {

                System.out.println("Insert position: ");

                //zaczytanie pozycji od gracza-serwera
                String number = scanner.nextLine();

                //dodanie pozycji do tablicy
                status = board.addMove(Integer.valueOf(number), "X");

                if (status) {
                    //wyswietlenie zaktualizowanego boardu
                    System.out.println("Current board:");
                    System.out.println(board);

                    //wysylamy klientowi pozycje, ktora wybralismy
                    socketOut.write(number + "\n");
                    socketOut.flush();

                    //sprawdzenie, czy po moim rochu nie nastapil koniec gry
                    //jesli nastapil, to wychodze z gry
                    flag = !board.isGameFinished();
                    if (!flag) {
                        //(board.getNumberOfSuccessfulMoves() == 9) ? System.out.println("End of game! remis!") : System.out.println("End of game! You win!");
                        if (board.getNumberOfSuccessfulMoves() == 9) System.out.println("End of game - draw."); else System.out.println("End of game! You win!");
                        break; //wyjscie z wewnetrznej petli
                    }

                } else {
                    System.out.println("Invalid position, insert again: ");
                }
            } while (!status);

            //jesli wygralem, to kolejnej iteracji zewnetrznej petli nie bedzie
            //bo zmienilem zmienna flag
            //ale nie chce tez, zeby obecna iteracja sie dokonczyla
            //wiec w takiej sytuacji tutaj dodatkowo przerywam zewnetrzna petle
            if (!flag) break;

            //czekamy na pozycje od klienta i te pozycje wpisujemy na board i zaznaczamy koleczkiem
            String oponentPosition = socketIn.nextLine();
            board.addMove(Integer.valueOf(oponentPosition), "O");

            //sprawdzenie, czy po moim rochu od klienta koniec gry
            //jesli nastapil, to wychodze z gry
            flag = !board.isGameFinished();
            if (!flag) {
                if (board.getNumberOfSuccessfulMoves() == 9) System.out.println("End of game - draw."); else System.out.println("End of game! You have lost :(");
                break;
            }

        }


    }
}
