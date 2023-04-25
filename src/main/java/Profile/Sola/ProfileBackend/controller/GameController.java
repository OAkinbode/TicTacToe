package Profile.Sola.ProfileBackend.controller;

import Profile.Sola.ProfileBackend.model.ScoreBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Profile.Sola.ProfileBackend.service.GameService;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping
    public String getExample() {
        return "This is an example GET request.";
    }

    @PostMapping(value = "/startgame/{name}")
    public ScoreBoard startGame(@PathVariable("name") String name) throws IOException {
        return gameService.setUpGameVersusComputer(name);
    }

    @PostMapping("/humanplayer/{move}")
    public ScoreBoard humanMove(@PathVariable("move") String move) throws IOException {
        System.out.println("HumanPlayer Api call succeeded");
        return gameService.playGameXPlayer(move);
    }

    @PostMapping("/computer")
    public ScoreBoard computerMove() throws IOException {
        return gameService.playGameOPlayer();
    }
}

