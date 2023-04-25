package Profile.Sola.ProfileBackend.model;

/**
 * Author: Olusola Akinbode
 * Class Game: Runner class for the game.
 */
import org.springframework.stereotype.Component;

import java.io.*;

public class Game implements Constants {
    public Board theBoard;
    public Referee theRef;

    /**
     * creates a board for the game
     */
    public Game( ) {
        theBoard  = new Board();
    }

    /**
     * calls the referee method runTheGame
     * @param r refers to the appointed referee for the game
     * @throws IOException
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.runTheGame();
    }
}
