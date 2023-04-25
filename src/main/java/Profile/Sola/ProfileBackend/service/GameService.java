package Profile.Sola.ProfileBackend.service;

import Profile.Sola.ProfileBackend.model.*;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GameService implements Constants {
    Referee theRef;
    Player xPlayer, oPlayer;
    Game theGame;

    boolean turnCounter;

    public GameService() {
        turnCounter = false;
    }

    public ScoreBoard setUpGameVersusComputer(String playerName) throws IOException {
        theGame = new Game();

        xPlayer = new HumanPlayer(playerName, 'X');
        oPlayer = new SmartPlayer("Computer", 'O');

        xPlayer.setBoard(theGame.theBoard);
        oPlayer.setBoard(theGame.theBoard);

        theRef = new Referee();
        theRef.setBoard(theGame.theBoard);
        theRef.setoPlayer(oPlayer);
        theRef.setxPlayer(xPlayer);
        oPlayer.setRef(theRef);
        xPlayer.setRef(theRef);
        theGame.appointReferee(theRef);
        return theRef.getBoard().getScoreBoard();
    }

    //This method exists only for the human player but also initializes the play of the Computer
    public ScoreBoard playGameXPlayer(String move) throws IOException {
        play(theRef.getxPlayer(), move);
        theRef.getxPlayer().checkWinner();
        return theRef.getBoard().getScoreBoard();
    }

    public void play(Player player, String move) throws IOException {
        if (!theRef.getBoard().oWins() && !theRef.getBoard().xWins() && !theRef.getBoard().isFull()) {
            player.makeMove(move);
        } else {
            player.checkWinner();
        }
    }

    //The method is used by the computer to return a play
    public ScoreBoard playGameOPlayer() throws IOException {
        theRef.getoPlayer().checkWinner();
        if (!theRef.getBoard().oWins() && !theRef.getBoard().xWins() && !theRef.getBoard().isFull()) {
            theRef.getoPlayer().play();
            theRef.getoPlayer().checkWinner();
        }
        return theRef.getBoard().getScoreBoard();
    }



}
