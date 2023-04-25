package project.sola.tictactoe.model;


import java.io.*;


public class HumanPlayer extends Player {

    public HumanPlayer(String name, char mark) {
        super(name, mark);
    }

    public void makeMove(String move) throws IOException{
        int row;
        int col;

        incrPlayCount(move);
        opponent.incrPlayCount(move);
        row = Character.getNumericValue(move.charAt(0));
        col = Character.getNumericValue(move.charAt(2));
        board.addMark(row, col, mark);
    }

}
