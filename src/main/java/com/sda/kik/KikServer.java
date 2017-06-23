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

        //otwieramy port
        ServerSocket serverSocket = new ServerSocket(1235);

        //serwer bedzie dzialal w nieskonczonej petli
        //po zakonczonej grze bedzie oczekiwal na kolejne polaczenie od klienta
        boolean flag = true;
        while (flag) {

            //nasluchujemy az do momentu kiedy jakis klient sie podlaczy
            System.out.println("Waiting for connection from client");
            Socket socket = serverSocket.accept();
            System.out.println("Connection established");

            //writer do wypisywania na zewnatrz
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //skaner do czytania wiadomosci od klienta
            Scanner socketIn = new Scanner(socket.getInputStream());

            //skaner do czytanie od usera-serwera
            Scanner scanner = new Scanner(System.in);

            Board board = new Board();

            System.out.println("You are first");

            //petla tak dlugo dopoki gra sie nie skonczy
            while (!board.isGameFinished()) {

                System.out.println("Current board:");
                System.out.println(board);

                //serwer zaczyna gre i ma parzyste ruchy
                if (board.getNumberOfSuccessfulMoves() % 2 == 0) {
                    yourTurn(socketOut, scanner, board);
                } else {
                    opponentsTurn(socketIn, board);
                }
            }
            System.out.println("Final board:");
            System.out.println(board);

            System.out.println("Finisking connection");
            socket.close();
        }//od duzego while

        serverSocket.close(); //w tej chwili nieosiagalne
    }

    private static void opponentsTurn(Scanner socketIn, Board board) {
        //czekamy na pozycje od klienta i te pozycje wpisujemy na board i zaznaczamy koleczkiem
        String oponentPosition = socketIn.nextLine();
        board.addMove(Integer.valueOf(oponentPosition), "O");
    }

    private static void yourTurn(BufferedWriter socketOut, Scanner scanner, Board board) throws IOException {
        boolean status;

        do {
            System.out.println("Insert position: ");

            //zaczytanie pozycji od gracza-serwera
            String number = scanner.nextLine();

            //dodanie pozycji do tablicy
            status = board.addMove(Integer.valueOf(number), "X");

            if (status) {
                //jesli udalo sie poprawnie dodac pozycje do tablicy to wysylamy klientowi te pozycje
                socketOut.write(number + "\n");
                socketOut.flush();
            } else {
                System.out.println("Invalid position, insert again: ");
            }
        } while (!status);
    }
}
