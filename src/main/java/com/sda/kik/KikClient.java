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
        while (!board.isGameFinished()) {

            System.out.println("Current board:");
            System.out.println(board);

            if (board.getNumberOfSuccessfulMoves() % 2 == 1) {
                yourTurn(socketOut, scanner, board);
            } else {
                opponentsTurn(socketIn, board);
            }

        }
        System.out.println("Final board:");
        System.out.println(board);

    }

    private static void yourTurn(BufferedWriter socketOut, Scanner scanner, Board board) throws IOException {
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
                /*
                flag = !board.isGameFinished();
                if (!flag) {
                    String message = (board.getNumberOfSuccessfulMoves() == 9) ? "End of game - draw." : "End of game! You win!";
                    System.out.println(message);
                    break; //wyjscie z wewnetrznej petli
                }
                */

            } else {
                System.out.println("Invalid position, insert again: ");
            }
        } while (!status);
    }

    private static void opponentsTurn(Scanner socketIn, Board board) {
        //zaczytanie pozycji od gracza-serwera
        String oponentPosition = socketIn.nextLine();

        //dodanie pozycji do tablicy
        Integer position = Integer.valueOf(oponentPosition);
        board.addMove(position, "X");

        //wyswietlenie tablicy
        //System.out.println("Updated board from serwer");
        //System.out.println(board);

        //sprawdzenie, czy po od serwera nie nastapil koniec gry
        //jesli nastapil, to wychodze z gry
            /*
            flag = !board.isGameFinished();
            if (!flag) {
                String message = (board.getNumberOfSuccessfulMoves() == 9) ? "End of game - draw." : "End of game! You have lost :(";
                System.out.println(message);
                break;
            }
            */
    }
}
