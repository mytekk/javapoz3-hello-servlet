package com.sda.kik;

/**
 * Created by RENT on 2017-06-20.
 */
public class Server {
    public static void main(String[] args) {

        Board board = new Board();
        System.out.println(board);

        board.addMove(5, "X");
        System.out.println(board);
        board.addMove(2, "O");
        System.out.println(board);

    }
}
