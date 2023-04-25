package Profile.Sola.ProfileBackend.model;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class SmartPlayer extends BlockingPlayer {

    public SmartPlayer(String name, char mark) {
        super(name, mark);

    }

    @Override
    public void play() throws IOException{
        boolean attackOrDefense = false;
        if(!board.oWins() && !board.xWins() && !board.isFull()){
            attackOrDefense = tryForWin();
            if (!attackOrDefense) {
                attackOrDefense = tryForBlock();
            }
            if (!attackOrDefense) {
                makeMove();
            }
        }
        else{
            checkWinner();
        }
    }

    @Override
    public void makeMove(){
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
        System.out.println("move made by computer: " + row + "" + col + "" + mark);
    }


    private boolean tryForWin() throws IOException{
        boolean winPos = false;
        for (int i = 0; i < 3; i++){
            if (winPos)
                break;
            for (int j = 0; j < 3; j++){
                winPos = testForPlaying(i, j);
                if (winPos){
                    position1 = i;
                    position2 = j;
                    break;
                }
            }
        }
        if (winPos)
            makeDeterminedMove();
        return winPos;
    }

    private boolean tryForBlock() throws IOException{
        boolean winPos = false;
        for (int i = 0; i < 3; i++){
            if (winPos)
                break;
            for (int j = 0; j < 3; j++){
                winPos = testForBlocking(i, j);
                if (winPos){
                    position1 = i;
                    position2 = j;
                    break;
                }
            }
        }
        if (winPos)
            makeDeterminedMove();
        return winPos;
    }

    protected boolean testForPlaying(int pos1, int pos2){
        boolean test = false;
        String move = pos1 + "," + pos2;
        int winner = 0;

        // test if location is filled
        if (!checkPlay(move) && !checkPlay(move)){
            board.checkMark(pos1, pos2, mark);
            winner = board.checkWinner(mark);
            board.checkMark(pos1, pos2, SPACE_CHAR);
        }
        if (winner == 1){
            test = true;
        }

        return test;
    }


}
