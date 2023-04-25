package Profile.Sola.ProfileBackend.model;

import java.io.IOException;

public abstract class Player {
    protected String name;
    protected Board board;
    protected Player opponent;
    protected char mark;
    protected Referee ref;
    protected int playCount;
    protected String plays[];
    abstract public void makeMove(String move) throws IOException;

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
        this.playCount = 0;
        this.plays = new String[9];
    }


    /**
     * @return String
     */
    public String getName() {
        return name;
    }


    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return Board
     */
    public Board getBoard() {
        return board;
    }


    /**
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;
    }


    /**
     * @return char
     */
    public char getMark() {
        return mark;
    }


    /**
     * @param mark
     */
    public void setMark(char mark) {
        this.mark = mark;
    }


    /**
     * @param opponent
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * @return Player
     */
    public Player getOpponent(){
        return this.opponent;
    }

    /**
     * @param ref
     */
    public void setRef(Referee ref){
        this.ref = ref;
    }

    // public String[] getPlay(){
    // 	return plays;
    // }

    /**
     * @throws IOException
     * Promises: initiates a play from a player.
     */
    public void play() throws IOException{
    }

    /*
     * Promises: Checks if a player has won.
     */
    public void checkWinner(){
        int winner = 0;
        if(board.oWins()){
            //Oplayer wins the game.
            winner = 1;
            board.setWinner(winner);
        }
        else if(board.xWins()){
            //Xplayer wins the game
            winner = 2;
            board.setWinner(winner);
        }
        else if(board.isFull()){
            // Game is a tie
            winner = 3;
            board.setWinner(winner);
        }

    }

    /**
     * @param move
     * @return boolean
     * Promises: a check on whether the location of a play has already been filled with a mark by another or same player.
     */
    protected boolean checkPlay(String move){
        boolean played = false;
        for (int i = 0; i < plays.length; i++){
            if (move.equals(plays[i]))
                played = true;
        }
        return played;
    }

    /**
     * @param move
     * Promises: blocking of a play location from further play.
     */
    protected void incrPlayCount(String move){
        plays[playCount] = move;
        playCount++;
    }

}
