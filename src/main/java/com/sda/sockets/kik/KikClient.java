package com.sda.sockets.kik;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by RENT on 2017-06-21.
 *
 * klient laczy sie do serwera i czeka na jego pierwszy ruch
 * klient dodaje "O" do tablicy
 */
public class KikClient {
    public static void main(String[] args) throws IOException {

        //tworzymy socket, podlaczamy sie do serwera
        Socket socket = new Socket("localhost", 1235);

        //writer do wypisywania na zewnatrz
        BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        //skaner do czytania tego co do nas przychodzi, czyli skaner tego co przychodzi do nas od Serwera
        Scanner socketIn = new Scanner(socket.getInputStream());

        //skaner do czytanie od usera-klienta
        Scanner scanner = new Scanner(System.in);

        Board board = new Board();

        System.out.println("Waiting for first move from serwer");

        //petla tak dlugo dopoki gra sie nie skonczy
        while (!board.isGameFinished()) {

            System.out.println("Current board:");
            System.out.println(board);

            //klient ma nieparzyste ruchy, czyli gre zaczyna serwer
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
            System.out.println("Insert position: ");

            //zaczytanie pozycji od gracza-klienta
            String ourPosition = scanner.nextLine();
            Integer ourPositionNumber = Integer.valueOf(ourPosition);

            //próba dodania pozycji do tablicy
            status = board.addMove(ourPositionNumber, "O");


            if (status) {
                //jesli udalo sie poprawnie dodac pozycje do tablicy to wysylamy serwerowi te pozycje
                socketOut.write(ourPositionNumber + "\n");
                socketOut.flush();
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
    }
}
