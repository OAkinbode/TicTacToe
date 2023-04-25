package project.sola.tictactoe.model;


import java.io.IOException;

public class BlockingPlayer extends RandomPlayer implements Constants{

    protected boolean win;
    protected int position1;
    protected int position2;


    public void makeMove2(String move) throws IOException {
        int row;
        int col;

        incrPlayCount(move);
        opponent.incrPlayCount(move);
        row = Character.getNumericValue(move.charAt(0));
        col = Character.getNumericValue(move.charAt(2));
        board.addMark(row, col, mark);
    }

    public BlockingPlayer(String name, char mark) {
        super(name, mark);
        position1 = 99;
        position2 = 99;
    }

    @Override
    public void play() throws IOException{
        if(!board.oWins() && !board.xWins() && !board.isFull()){
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
            else{
                makeMove();
            }
//            board.display();
//            opponent.play();

        }
        else{
            checkWinner();
            System.exit(0);
        }
    }

    protected void makeDeterminedMove() throws IOException{
        String move = position1 + "," + position2;
        incrPlayCount(move);
        opponent.incrPlayCount(move);
        board.addMark(position1, position2, mark);
        System.out.println("move made by computer: " + position1 + "" + position2 + "" + mark);
    }

    protected boolean testForBlocking(int pos1, int pos2){
        boolean test = false;
        String move = pos1 + "," + pos2;
        int winner = 0;

        // test if location is filled
        if (!checkPlay(move) && !opponent.checkPlay(move)){
            board.checkMark(pos1, pos2, opponent.mark);
            winner = board.checkWinner(opponent.mark);
            board.checkMark(pos1, pos2, SPACE_CHAR);
        }
        if (winner == 1){
            test = true;
        }

        return test;
    }
}
