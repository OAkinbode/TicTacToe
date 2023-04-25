package project.sola.tictactoe.model;


import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Author: Olusola Akinbode
 * Class Board: Containing the game logic.
 *
 */

public class Board implements Constants{
    private char theBoard[][];
    private int markCount;

    private ScoreBoard scoreBoard;

    public Board() {
        markCount = 0;
        theBoard = new char[3][];
        for (int i = 0; i < 3; i++) {
            theBoard[i] = new char[3];
            for (int j = 0; j < 3; j++)
                theBoard[i][j] = SPACE_CHAR;
        }
        scoreBoard = new ScoreBoard("","","","","","",
                "","","","","", "", 0);
    }

    /**
     * @param row
     * @param col
     * @return char
     * Requires: row and column information
     * Promises: Marked locations from the game array.
     */
    public char getMark(int row, int col) {
        return theBoard[row][col];
    }

    /**
     * @return boolean
     * Promises: checks if all possible locations in the game are played.
     */
    public boolean isFull() {
        return markCount == 9;
    }

    /**
     * @return boolean
     * Promises: checks if player x has won the game.
     */
    public boolean xWins() {
        if (checkWinner(LETTER_X) == 1)
            return true;
        else
            return false;
    }


    /**
     * @return boolean
     * Promises: checks if player O has won the game.
     */
    public boolean oWins() {
        if (checkWinner(LETTER_O) == 1)
            return true;
        else
            return false;
    }

    /**
     * Promises: displays a formated arrangement of the marks x and O on the terminal.
     */
    public ArrayList<String> display() {
        return scoreBoard.display();
    }

    public ScoreBoard getScoreBoard(){
        return scoreBoard;
    }


    /**
     * @param row
     * @param col
     * @param mark
     * Requires: row and column information and the right mark for the player.
     * Promises: to add that mark to the right location on the board.
     */
    public void addMark(int row, int col, char mark) {
        theBoard[row][col] = mark;
        markCount++;
        scoreBoard.addPlay(mark+""+row+""+col+"");
    }

    public void checkMark(int row, int col, char mark) {
        theBoard[row][col] = mark;
        markCount++;
    }



    public void clear() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                theBoard[i][j] = SPACE_CHAR;
        markCount = 0;
        scoreBoard.clearBoard();
    }

    public void setWinner(int winner)
    {scoreBoard.setWinner(winner);}


    /**
     * @param mark
     * @return int
     * Requires: the mark for the player.
     * Promises: to check if the player corresponding to the mark has won the game.
     */
    int checkWinner(char mark) {
        int row, col;
        int result = 0;

        for (row = 0; result == 0 && row < 3; row++) {
            int row_result = 1;
            for (col = 0; row_result == 1 && col < 3; col++)
                if (theBoard[row][col] != mark)
                    row_result = 0;
            if (row_result != 0)
                result = 1;
        }


        for (col = 0; result == 0 && col < 3; col++) {
            int col_result = 1;
            for (row = 0; col_result != 0 && row < 3; row++)
                if (theBoard[row][col] != mark)
                    col_result = 0;
            if (col_result != 0)
                result = 1;
        }

        if (result == 0) {
            int diag1Result = 1;
            for (row = 0; diag1Result != 0 && row < 3; row++)
                if (theBoard[row][row] != mark)
                    diag1Result = 0;
            if (diag1Result != 0)
                result = 1;
        }
        if (result == 0) {
            int diag2Result = 1;
            for (row = 0; diag2Result != 0 && row < 3; row++)
                if (theBoard[row][3 - 1 - row] != mark)
                    diag2Result = 0;
            if (diag2Result != 0)
                result = 1;
        }
        return result;
    }

}
