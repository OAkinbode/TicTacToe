package project.sola.tictactoe.model;


import org.springframework.stereotype.Component;

import java.io.IOException;

public class RandomPlayer extends Player {

    @Override
    public void makeMove(String move) throws IOException {

    }

    public RandomPlayer(String name, char mark) {
        super(name, mark);
    }

    public void makeMove() throws IOException{
        int row;
        int col;
        String move;
        mark = 'O';
        RandomGenerator generate = new RandomGenerator();

        do{
            row = generate.discrete(0,2);
            col = generate.discrete(0, 2);
            move = row + "," + col;
        }
        while(checkPlay(move) || opponent.checkPlay(move));

        incrPlayCount(move);
        opponent.incrPlayCount(move);
        row = Character.getNumericValue(move.charAt(0));
        col = Character.getNumericValue(move.charAt(2));
        board.addMark(row, col, mark);
    }
}
