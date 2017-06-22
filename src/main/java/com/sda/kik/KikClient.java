package com.sda.kik;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-21.
 */
public class KikClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1235);
        BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        Scanner socketIn = new Scanner(socket.getInputStream());
        Scanner scanner = new Scanner(System.in); //skaner do czytanie od usera-klienta

        Board board = new Board();

        boolean flag = true;
        System.out.println("Waiting for first move from serwer");
        while (flag) {
            //zaczytanie pozycji od gracza-serwera
            String oponentPosition = socketIn.nextLine();

            //dodanie pozycji do tablicy
            Integer position = Integer.valueOf(oponentPosition);
            board.addMove(position, "X");

            //wyswietlenie tablicy
            System.out.println("Updated board from serwer");
            System.out.println(board);

            //sprawdzenie, czy po od serwera nie nastapil koniec gry
            //jesli nastapil, to wychodze z gry
            flag = !board.isGameFinished();
            if (!flag) {
                if (board.getNumberOfSuccessfulMoves() == 9) System.out.println("End of game - draw."); else System.out.println("End of game! You have lost :(");
                break;
            }

            boolean status;
            do {
                //klient podaje swoja pozycje
                System.out.println("Insert position: ");

                //przerabiam podana pozycje na Integer
                String ourPosition = scanner.nextLine();
                Integer ourPositionNumber = Integer.valueOf(ourPosition);

                //próba dodania pozycji do tablicy
                status = board.addMove(ourPositionNumber, "O");
                if (status) {
                    //udało sie, wiec wyslanie naszje pozycji userowi-serwerowi
                    socketOut.write(ourPositionNumber + "\n");
                    socketOut.flush();

                    //sprawdzenie, czy po moim rochu nie nastapil koniec gry
                    //jesli nastapil, to wychodze z gry
                    flag = !board.isGameFinished();
                    if (!flag) {
                        if (board.getNumberOfSuccessfulMoves() == 9) System.out.println("End of game - draw."); else System.out.println("End of game! You win!");
                        break; //wyjscie z wewnetrznej petli
                    }

                } else {
                    System.out.println("Invalid position, insert again: ");
                }
            } while (!status);

            //wyslanie naszje pozycji userowi-serwerowi
            //socketOut.write(ourPositionNumber + "\n");
            //socketOut.flush();

            //wyswietlenie zaktualizowanego boardu
            System.out.println("Current board:");
            System.out.println(board);



        }

    }
}
